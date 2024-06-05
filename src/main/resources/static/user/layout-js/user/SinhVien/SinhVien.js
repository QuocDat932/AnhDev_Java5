class SinhVien {
    listSinhVien = []
    listChuyenNganh = []
    loadInit = async () => {
        await this.getListSinhVien()

    }

    getListSinhVien = async () => {
        let param = {
            filterMssv : $('#idFilterMssv').val(),
            filterHoVaTen : $('#idFilterHoTen').val(),
            filterChuyenNganh : $('#idFilterChuyenNganh').val()
        }

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
            hocKy : e.hocKi.ma_hk
        }))

        await this.getListChuyenNganh()
        this.createTableSinhVien()
    }

    getListChuyenNganh = async () => {
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
    }

    createTableSinhVien = () => {
        // Hủy khởi tạo DataTable nếu nó đã tồn tại
        if ($.fn.dataTable.isDataTable('#tableSinhVien')) {
            $('#tableSinhVien').DataTable().destroy();
        }

        let body = ``
        let select = ``
        this.listSinhVien.forEach((e, index) => {
            body +=   `<tr>
                           <td class="bg-transparent text-center">${index+1}</td> 
                           <td class="bg-transparent text-center">${e.mssv}</td> 
                           <td class="bg-transparent text-center">${e.hoVaTen}</td> 
                           <td class="bg-transparent text-center">${e.chuyenNganh}</td> 
                           <td class="bg-transparent text-center">${e.gioiTinh? 'Nam' : 'Nữ'}</td> 
                           <td class="bg-transparent text-center">${e.hocKy}</td> 
                       </tr>`
        })

        this.listChuyenNganh.forEach((String, index) =>{
            select += `<option value="index">${String}</option>`
        })

        let footer = `</tbody></table>`
        let result = body +footer
        let result2 = select + footer;
        $('.tableSinhVien').html(result)
        $('.form-select').html(result2)
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
    }

    getSinhVienByMssv = async (mssv) => {
        let param = {
            mssv: mssv
        }

        let {data: response} = await axios.get('java05/sinhvien-api/getSinhVienByMSSV',
            {params: param})

        if (response.success) {
            this.fillDataToForm(response.data)
        }
    }

    fillDataToForm = (sinhVien) => {
        $('#idMssv').val(sinhVien.mssv)
        $('#idHoTen').val(sinhVien.hoVaTen)
        $('#idChuyenNganh').val(sinhVien.chuyenNganh)
        $('#idHocKy').val(sinhVien.hocKy)
        if(sinhVien.gioiTinh){
            $('#idNam').prop('checked', true)
        } else {
            $('#idNu').prop('checked', true)
        }
    }

    clearForm = () => {
        this.fillDataToForm({})
        $('#idNam').prop('checked', true)
        $('#idXacNhan').prop('checked', false)
    }

    btnLuu_click = async () => {
        if( !$('#idXacNhan').prop('checked')){
            Swal.fire({
                title: 'Vui lòng chọn xác nhận',
                icon: 'warning',
                showConfirmButton: false,
                timer: 1500
            })
            return
        }
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

        console.log(response)

        if (!response.success) {
            // let {dataUpdate : reponseUpdate} = await axios.put('/java05/sinhvien-api/updateSinhVien', dataApiSave)
            Swal.fire({
                title: 'Sinh viên đã tồn tại',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })

        } else{
            Swal.fire({
                title: 'Lưu thông tin sinh viên thành công',
                icon: 'success',
                showConfirmButton: false,
                timer: 1500
            })

        }


        await this.getListSinhVien()
    }

    btnCapNhat_click = async () => {
        if( !$('#idXacNhan').prop('checked')){
            Swal.fire({
                title: 'Vui lòng chọn xác nhận',
                icon: 'warning',
                showConfirmButton: false,
                timer: 1500
            })
            return
        }
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

        let {data : response} = await axios.put('/java05/sinhvien-api/updateSinhVien', dataApiSave)
        if (!response.success) {
            Swal.fire({
                title: 'Sinh viên không tồn tại',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
        } else{
            Swal.fire({
                title: 'Cập nhật thông tin thành công',
                icon: 'success',
                showConfirmButton: false,
                timer: 1500
            })

        }
        await this.getListSinhVien()
    }

    btnXoa_click = async () => {
        if( !$('#idXacNhan').prop('checked')){
            Swal.fire({
                title: 'Vui lòng chọn xác nhận',
                icon: 'warning',
                showConfirmButton: false,
                timer: 1500
            })
            return
        }
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
            await this.getListSinhVien()
        });
    }

    turnOffXacNhan = () => {
        $('#idXacNhan').prop('checked', false)
    }

    validateForm = () => {
        if (!$('#idMssv').val()) {
            this.turnOffXacNhan()
            Swal.fire({
                title: 'Mã số sinh viên còn trống',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return false
        }
        if ($('#idMssv').val().length >= 8) {
            this.turnOffXacNhan()
            Swal.fire({
                title: 'Mã số sinh viên quá dài',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return false
        }
        if (!$('#idHoTen').val()) {
            this.turnOffXacNhan()
            Swal.fire({
                title: 'Họ tên còn trống',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return false
        }
        if (!$('#idChuyenNganh').val()) {
            this.turnOffXacNhan()
            Swal.fire({
                title: 'Chuyên ngành còn trống',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return false
        }

        if (!$('#idHocKy').val()) {
            this.turnOffXacNhan()
            Swal.fire({
                title: 'Học Kỳ còn trống',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return false
        }
        return true
    }
}