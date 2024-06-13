class SinhVien {
    listSinhVien = []
    listChuyenNganh = []
    listHocKy = []

    loadInit = async () => {
        await this.getListSinhVien()
    }

    getListSinhVien = async () => {
        let {data : response} = await axios.get('/java05/sinhvien-api/getAllSinhVien')
        if (!response.success) {
            Swal.fire({
                title: response.message,
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return
        }
        this.listSinhVien = response.data.map(e => ({
            mssv : e.mssv,
            hoVaTen : e.hoVaTen,
            chuyenNganh : e.chuyenNganh,
            gioiTinh : e.gioiTinh,
            maHocKy : e.hocKi.maHk,
            tenHocKy : e.hocKi.tenHocKi,
        }))
        await this.createSelectChuyenNganh()
        await this.createSelectHocKy()
        this.createTableSinhVien()
    }

    createSelectHocKy = async  () => {
        let {data : response} = await axios.get('/java05/hocki-api/getAllHocKy')
        if (!response.success) {
            Swal.fire({
                title: response.message,
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return
        }
        this.listHocKy = response.data
        let select = ``
        this.listHocKy.forEach((hocKi) =>{
            select +=`<option value="${hocKi.maHk}">${hocKi.tenHocKi}</option>`
        })
        $('#idHocKy').html(select)
    }

    createTableSinhVien = () => {
        if ($.fn.dataTable.isDataTable('#tableSinhVien')) {
            $('#tableSinhVien').DataTable().destroy();
        }
        let body = ``
        this.listSinhVien.forEach((e, index) => {
            body +=   `<tr>
                           <td class="bg-transparent text-center">${index+1}</td> 
                           <td class="bg-transparent text-center">${e.mssv}</td> 
                           <td class="bg-transparent text-center">${e.hoVaTen}</td> 
                           <td class="bg-transparent text-center">${e.chuyenNganh}</td> 
                           <td class="bg-transparent text-center">${e.gioiTinh? 'Nam' : 'Nữ'}</td> 
                           <td class="bg-transparent text-center" value="${e.maHocKy}">${e.tenHocKy}</td> 
                       </tr>`
        })
        let footer = `</tbody></table>`
        let result = body +footer
        $('.tableSinhVien').html(result)
        let table = new DataTable('#tableSinhVien', {
            searching: false,
            info: false,
            paging: true,
            ordering: false,
            lengthMenu: [10]
        })
        $('.dt-length').hide()
        let selt = this
        //Gán lại sự kiện double click cho hàng trong bảng
        table.off('dblclick').on('dblclick', 'tbody tr', function () {
            let data = table.row(this).index();
            selt.fillDataToForm(selt.listSinhVien[data])
        });
        this.fillDataToForm(this.listSinhVien[0])
    }

    createSelectChuyenNganh = async () => {
        let {data : response} = await axios.get('/java05/sinhvien-api/getListChuyenNganh')
        if (!response.success) {
            Swal.fire({
                title: response.message,
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return
        }
        this.listChuyenNganh = response.data;
        let select = `<option value=""></option>`
        this.listChuyenNganh.forEach((maChuyenNganh) =>{
            select +=`<option value="${maChuyenNganh}">${maChuyenNganh}</option>`
        })
        $('.form-select').html(select)
    }

    filterSinhVien = async () => {
        let param = {
            mssv : $('#idFilterMssv').val(),
            hoVaTen : $('#idFilterTen').val(),
            chuyenNganh : $('#idFilterChuyenNganh').val()
        }
        let {data: response} = await axios.get('/java05/sinhvien-api/getListSinhVienByFilter',
            {params: param})
        this.listSinhVien = response.data.map(e => ({
            mssv : e.mssv,
            hoVaTen : e.hoVaTen,
            chuyenNganh : e.chuyenNganh,
            gioiTinh : e.gioiTinh,
            maHocKy : e.hocKi.maHk,
            tenHocKy : e.hocKi.tenHocKi,
        }))
        if (!response.data.length) {
            Swal.fire({
                title: 'Không tìm thấy sinh viên',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return
        }
        this.createTableSinhVien();
    }

    fillDataToForm = (sinhVien) => {
        $('#idMssv').val(sinhVien.mssv)
        $('#idHoTen').val(sinhVien.hoVaTen)
        $('#idChuyenNganh').val(sinhVien.chuyenNganh)
        $('#idHocKy').val(sinhVien.maHocKy)
        if(!sinhVien.gioiTinh){
            $('#idNu').prop('checked', true)
            return
        }
        $('#idNam').prop('checked', true)
    }

    clearForm = () => {
        this.fillDataToForm({})
        $('#idNam').prop('checked', true)
    }

    btnLuu_click = async () => {
        if (!this.validateForm()) {
            return
        }
        let dataApiSave = {
            mssv : $('#idMssv').val(),
            hoVaTen : $('#idHoTen').val(),
            chuyenNganh : $('#idChuyenNganh').val(),
            gioiTinh : $('#idNam').prop('checked'),
            hocKi : $('#idHocKy').val()
        }
        let {data : response} = await axios.post('/java05/sinhvien-api/saveSinhVien',
            dataApiSave)
        if (!response.success) {
            Swal.fire({
                title: 'Lưu không thành công',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return
        }
        Swal.fire({
            title: 'Lưu thông tin sinh viên thành công',
            icon: 'success',
            showConfirmButton: false,
            timer: 1500
        })
        await this.getListSinhVien()
    }

    btnXoa_click = async () => {
        if (!$('#idMssv').val()) {
            Swal.fire({
                title: 'Chưa có mã số sinh viên',
                icon: 'warning',
                showConfirmButton: false,
                timer: 1500
            })
            return
        }
        Swal.fire({
            title: "Chắc chắn muốn xóa?",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Xóa",
            cancelButtonText: "Vẫn giữ"
        }).then(async () => {
            let param = {
                mssv : $('#idMssv').val()
            }
            let {data: response} = await axios.delete('/java05/sinhvien-api/deleteSinhVienByMssv', {params: param})
            if (!response.success) {
                Swal.fire({
                    title: 'Xóa không thành công',
                    icon: 'error',
                    showConfirmButton: false,
                    timer: 1500
                })
                return
            }
            Swal.fire({
                title: 'Xóa thành công',
                icon: 'success',
                showConfirmButton: false,
                timer: 1500
            })
            this.clearForm()
            await this.createTableSinhVien()
        });
    }

    verifyForm = () => {
        let value = $('#idXacNhan').prop('checked')
        $('#btnLuu').prop('disabled', !value)
        $('#btnXoa').prop('disabled', !value)

        $('#idMssv').prop('disabled', value)
        $('#idHoTen').prop('disabled', value)
        $('#idChuyenNganh').prop('disabled', value)
        $('#idHocKy').prop('disabled', value)
        $('#idNam').prop('disabled', value)
        $('#idNu').prop('disabled', value)
    }

    validateForm = () => {
        if (!$('#idMssv').val()) {
            Swal.fire({
                title: 'Mã số sinh viên còn trống',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return false
        }
        if ($('#idMssv').val().length > 7) {
            Swal.fire({
                title: 'Mã số sinh viên quá dài',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return false
        }
        if (!$('#idHoTen').val()) {
            Swal.fire({
                title: 'Họ tên còn trống',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return false
        }
        if (!$('#idChuyenNganh').val()) {
            Swal.fire({
                title: 'Chuyên ngành còn trống',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return false
        }
        return true
    }
}