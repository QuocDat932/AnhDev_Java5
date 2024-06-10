class BangDiem {
    listResult = []
    hasSysId = null
    listMaMonHoc = []

    loadInit = async () => {
        await this.getListResult()
    }

    getListResult = async () => {
        let {data : response} = await axios.get('/java05/lichsuhoctap-api/getAllLichSuHocTap')

        if (!response.success) {
            Swal.fire({
                title: response.message,
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return
        }
        this.listResult = response.data.map(e => ({
            sysId: e.sysId,
            mssv: e.mssv.mssv,
            hoVaTen : e.mssv.hoVaTen,
            lanThi : e.lanThi,
            monHoc : e.monHoc.maMonHoc,
            hocKi : e.hocKi.ma_hk,
            diemThi : e.diemThi,
            ketQua : e.ketQua
        }))
        await this.getListMaMonHoc()
        this.createTableBangDiem()
    }

    getListMaMonHoc = async () => {
        let {data : response} = await axios.get('/java05/monhoc-api/getAllMonHoc')
        if (!response.success) {
            Swal.fire({
                title: response.message,
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return
        }
        this.listMaMonHoc = response.data
    }

    createTableBangDiem = () => {
        // Hủy khởi tạo DataTable nếu nó đã tồn tại
        if ($.fn.dataTable.isDataTable('#tableBangDiem')) {
            $('#tableBangDiem').DataTable().destroy();
        }
        let body = ``
        let select = `<option value="0"></option>`
        this.listResult.forEach((e, index) => {
            body +=   `<tr>
                           <td class="bg-transparent text-center">${index+1}</td> 
                           <td class="bg-transparent text-center">${e.mssv}</td> 
                           <td class="bg-transparent text-center">${e.hoVaTen}</td> 
                           <td class="bg-transparent text-center">${e.lanThi}</td> 
                           <td class="bg-transparent text-center">${e.monHoc}</td> 
                           <td class="bg-transparent text-center">${e.hocKi}</td> 
                           <td class="bg-transparent text-center">${e.diemThi}</td> 
                       </tr>`
        })
        let footer = `</tbody></table>`
        let result = body +footer
        this.listMaMonHoc.forEach((monHoc, index) =>{
            select +=`<option value="index+1">${monHoc.maMonHoc}</option>`
        })
        $('.tableBangDiem').html(result)
        $('.form-select').html(select)
        let table = new DataTable('#tableBangDiem', {
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
            selt.fillDataToForm(selt.listResult[data])
            selt.hasSysId = selt.listResult[data].sysId;
            $('#btnCapNhat').prop('disabled', false)
            $('#btnLuu').prop('disabled', true)
            $('#btnXoa').prop('disabled', false)
        });
        $('#btnCapNhat').prop('disabled', true)
        $('#btnLuu').prop('disabled', false)
        $('#btnXoa').prop('disabled', true)
    }

    fillDataToForm = (bangDiem) => {
        $('#idMssv').val(bangDiem.mssv)
        $('#idHoTen').val(bangDiem.hoVaTen)
        $('#idMonHoc').val(bangDiem.monHoc)
        $('#idHocKy').val(bangDiem.hocKi)
        $('#idLanThi').val(bangDiem.lanThi)
        $('#idDiemThi').val(bangDiem.diemThi)
        $('#idKetQua').text(bangDiem.ketQua)
        if(bangDiem.diemThi >= 5){
            $('#idKetQua').attr('class', 'badge text-bg-success fs-4')
        } else {
            $('#idKetQua').attr('class', 'badge text-bg-danger fs-4')
        }
    }

    xuatKetQua = () =>{
        let inputDiem = $('#idDiemThi').val()
        this.turnOffXacNhan();
        if (inputDiem < 5 && inputDiem >= 0 && inputDiem != '') {
            $('#idKetQua').text("Failed").attr('class', 'badge text-bg-danger fs-4')
        } else if (inputDiem >=5 && inputDiem <= 10) {
            $('#idKetQua').text("Passed").attr('class', 'badge text-bg-success fs-4')
        } else {
            $('#idKetQua').text("Kết quả").attr('class', 'badge text-bg-danger fs-4')
        }
    }

    clearForm = () =>{
        this.hasSysId = null
        this.fillDataToForm({})
        $('#btnCapNhat').prop('disabled', true)
        $('#btnLuu').prop('disabled', false)
        $('#btnXoa').prop('disabled', true)
        $('#idKetQua').text('Kết quả').attr('class', 'badge text-bg-danger fs-4')
        $('#idXacNhan').prop('checked', false)
    }

    btnLuu_click = async (isUpdate) => {
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
            sysId : isUpdate ? this.hasSysId : null,
            mssv : $('#idMssv').val(),
            lanThi : $('#idLanThi').val(),
            diemThi : $('#idDiemThi').val(),
            ketQua : $('#idKetQua').text(),
            monHoc : $('#idMonHoc').val(),
            hocKi : $('#idHocKy').val()
        }
        let {data : response} = await axios.post('/java05/lichsuhoctap-api/saveLichSuHocTap',
            dataApiSave)
        if (isUpdate) {
            Swal.fire({
                title: 'Cập nhật thành công',
                icon: 'success',
                showConfirmButton: false,
                timer: 1500
            })
        } else{
            Swal.fire({
                title: 'Lưu thông tin bảng điểm thành công',
                icon: 'success',
                showConfirmButton: false,
                timer: 1500
            })
        }
        await this.getListResult()
    }

    btnXoa_click = async () =>{
        if( !$('#idXacNhan').prop('checked')){
            Swal.fire({
                title: 'Vui lòng chọn xác nhận',
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
                sysId : this.hasSysId
            }
            let {data: response} = await axios.delete('/java05/lichsuhoctap-api/deleteLichSuHocTapBySysId', {params: param})
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
            await this.getListResult()
        });
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
        if (!$('#idMonHoc').val()) {
            this.turnOffXacNhan()
            Swal.fire({
                title: 'Môn học còn trống',
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
        if (!$('#idLanThi').val()) {
            this.turnOffXacNhan()
            Swal.fire({
                title: 'Lần thi còn trống',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return false
        }
        if (!$('#idDiemThi').val()) {
            this.turnOffXacNhan()
            Swal.fire({
                title: 'Điểm thi không hợp lệ',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return false
        }
        return true
    }

    turnOffXacNhan = () => {
        $('#idXacNhan').prop('checked', false)
    }

    getListBangDiemByFilter = async (mssv, maMonHoc, ketQua) => {
        let param = {
            mssv : mssv,
            maMonHoc: maMonHoc,
            ketQua : ketQua
        }
        let {data: response} = await axios.get('/java05/lichsuhoctap-api/getListBangDiemByFilter',
            {params: param})
        if (!response.success) {
            Swal.fire({
                title: 'Không tìm thấy sinh viên',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return
        }
        this.listResult = response.data.map(e => ({
            sysId: e.sysId,
            mssv: e.mssv.mssv,
            hoVaTen : e.mssv.hoVaTen,
            lanThi : e.lanThi,
            monHoc : e.monHoc.maMonHoc,
            hocKi : e.hocKi.ma_hk,
            diemThi : e.diemThi,
            ketQua : e.ketQua
        }))
        this.createTableBangDiem();
    }

    filterBangDiem = async () =>{
        let param = {
            filterMssv : $('#idFilterMssv').val(),
            filterMonHoc : $('#idFilterMonHoc option:selected').text(),
            filterCheck : 'Failed'
        }
        if(!$('#idFilterFail').prop('checked')){
            param.filterCheck = ''
        } else {
            param.filterCheck = 'Failed'
        }
        this.getListBangDiemByFilter(param.filterMssv, param.filterMonHoc, param.filterCheck)
    }
}