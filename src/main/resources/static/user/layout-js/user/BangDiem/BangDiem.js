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
        await this.createSelectMonHoc()
        this.createTableBangDiem()
    }

    createSelectMonHoc = async () => {
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
        let select = `<option value="0"></option>`
        this.listMaMonHoc.forEach((monHoc, index) =>{
            select +=`<option value="monHoc.maMonHoc">${monHoc.maMonHoc}</option>`
        })
        $('.form-select').html(select)
    }

    createTableBangDiem = () => {
        if ($.fn.dataTable.isDataTable('#tableBangDiem')) {
            $('#tableBangDiem').DataTable().destroy();
        }
        let body = ``
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
        $('.tableBangDiem').html(result)
        let table = new DataTable('#tableBangDiem', {
            searching: false,
            info: false,
            paging: true,
            ordering: false,
            lengthMenu: [10]
        })
        $('.dt-length').hide()
        let selt = this
        table.off('dblclick').on('dblclick', 'tbody tr', function () {
            let data = table.row(this).index();
            selt.fillDataToForm(selt.listResult[data])
            selt.hasSysId = selt.listResult[data].sysId;
        });
        this.fillDataToForm(this.listResult[0])
    }

    filterBangDiem = async () =>{
        let param = {
            mssv : $('#idFilterMssv').val(),
            maMonHoc : $('#idFilterMonHoc option:selected').text(),
            ketQua : 'Failed'
        }
        if(!$('#idFilterFail').prop('checked')){
            param.ketQua = ''
        } else {
            param.ketQua = 'Failed'
        }
        let {data: response} = await axios.get('/java05/lichsuhoctap-api/getListBangDiemByFilter',
            {params: param})
        if (response.data.length === 0) {
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

    fillDataToForm = (bangDiem) => {
        $('#idMssv').val(bangDiem.mssv)
        $('#idHoTen').val(bangDiem.hoVaTen)
        $('#idMonHoc').val(bangDiem.monHoc)
        $('#idHocKy').val(bangDiem.hocKi)
        $('#idLanThi').val(bangDiem.lanThi)
        $('#idDiemThi').val(bangDiem.diemThi)
        this.setResult()
    }

    setResult = () =>{
        let inputDiem = $('#idDiemThi').val()
        if (inputDiem < 5 && inputDiem >= 0 && inputDiem !== '') {
            $('#idKetQua').text("Rớt môn").attr('class', 'badge text-bg-danger fs-4').val("Failed")
        } else if (inputDiem >=5 && inputDiem <= 10) {
            $('#idKetQua').text("Qua môn").attr('class', 'badge text-bg-success fs-4').val("Passed")
        } else {
            $('#idKetQua').text("Kết quả").attr('class', 'badge text-bg-info fs-4')
        }
    }

    clearForm = () =>{
        this.hasSysId = null
        this.fillDataToForm({})
        $('#idKetQua').text("Kết quả").attr('class', 'badge text-bg-info fs-4')
    }

    btnLuu_click = async (isUpdate) => {
        if (!this.validateForm()) {
            return
        }
        let dataApiSave = {
            sysId : isUpdate ? this.hasSysId : null,
            mssv : $('#idMssv').val(),
            lanThi : $('#idLanThi').val(),
            diemThi : $('#idDiemThi').val(),
            ketQua : $('#idKetQua').val(),
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

    verifyForm = () => {
        let value = $('#idXacNhan').prop('checked')
        $('#btnLuu').prop('disabled', !value)
        $('#btnCapNhat').prop('disabled', !value)
        $('#btnXoa').prop('disabled', !value)

        $('#idMssv').prop('disabled', value)
        $('#idHoTen').prop('disabled', value)
        $('#idMonHoc').prop('disabled', value)
        $('#idHocKy').prop('disabled', value)
        $('#idLanThi').prop('disabled', value)
        $('#idDiemThi').prop('disabled', value)
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
        if ($('#idMssv').val().length >= 8) {
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
        if (!$('#idMonHoc').val()) {
            Swal.fire({
                title: 'Môn học còn trống',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return false
        }
        if (!$('#idHocKy').val()) {
            Swal.fire({
                title: 'Học Kỳ còn trống',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return false
        }
        if (!$('#idLanThi').val()) {
            Swal.fire({
                title: 'Lần thi còn trống',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return false
        }
        if (!$('#idDiemThi').val()) {
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
}