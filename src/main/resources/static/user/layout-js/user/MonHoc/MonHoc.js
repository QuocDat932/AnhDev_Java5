class MonHoc {
    listMonHoc = []

    loadInit = () => {
        this.getListMonHoc()
    }
    getListMonHoc = async () => {
        let param = {
            maMonHoc : $('#idFilterMaMonHoc').val()
        }
        let response;
        if (param.maMonHoc) {
            response = await axios.get("/java05/monhoc-api/getMonHocByMaMonHoc", { params: param });
        } else {
            response = await axios.get('/java05/monhoc-api/getAllMonHoc');
        }

        let { data } = response;

        if (!data.success) {
            Swal.fire({
                title: response.message,
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return
        }
        this.listMonHoc = data.data.map(e => ({
            maMonHoc : e.maMonHoc,
            tenMonHoc : e.tenMonHoc,
            soTinChi : e.soTinChi
        }))
        this.createTableMonHoc()
    }
    createTableMonHoc = () => {
        let header = `<table class="table table-responsive table-bordered table-hover border-black m-0 w100" id="tableMonHoc">
                                <thead>
                                    <th class="text-center">No.</th>
                                    <th class="text-center">Mã môn học</th>
                                    <th class="text-center">Tên môn học</th>
                                    <th class="text-center">Số tín chỉ</th>
                                </thead>
                                <tbody>`
        let body = ``
        this.listMonHoc.forEach((e, index) => {
            body +=   `<tr>
                           <td class="bg-transparent text-center">${index + 1}</td> 
                           <td class="bg-transparent text-center">${e.maMonHoc}</td> 
                           <td class="bg-transparent text-center">${e.tenMonHoc}</td> 
                           <td class="bg-transparent text-center">${e.soTinChi}</td> 
                       </tr>`
        })
        let footer = `</tbody></table>`
        let result = header + body +footer
        $('.tableMonHoc').html(result)
        let table = new DataTable('#tableMonHoc', {
            searching: false,
            info: false,
            paging: true,
            ordering: false,
            lengthMenu: [10]
        })
        $('.dt-length').hide()
        let safe = this
        table.on('dblclick', 'tbody tr', function () {
            let data = table.row(this).data();
            safe.fillDataToForm({
                maMonHoc : data[1],
                tenMonHoc : data[2],
                soTinChi : data[3]
            })
        });
        this.fillDataToForm(this.listMonHoc[0])
    }
    fillDataToForm = (monHoc) => {
        $('#idMaMonHoc').val(monHoc.maMonHoc)
        $('#idTenMonHoc').val(monHoc.tenMonHoc)
        $('#idSoTinChi').val(monHoc.soTinChi)
    }
    btnClearForm_click = () => {
        $('#idMaMonHoc').val('')
        $('#idTenMonHoc').val('')
        $('#idSoTinChi').val('')
        $('#idXacNhan').prop('checked', false)
        this.verifyForm()
    }
    verifyForm = () => {
        let value = $('#idXacNhan').prop('checked')
        $('#idBtnLuu').prop('disabled', !value)
        $('#idBtnXoa').prop('disabled', !value)

        $('#idMaMonHoc').prop('disabled', value)
        $('#idTenMonHoc').prop('disabled', value)
        $('#idSoTinChi').prop('disabled', value)
    }
    validateForm = () => {
        if (!$('#idMaMonHoc').val()) {
            Swal.fire({
                title: 'Mã môn học còn trống',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return false
        }
        if (!$('#idTenMonHoc').val()) {
            Swal.fire({
                title: 'Tên môn học còn trống',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return false
        }
        if (!$('#idSoTinChi').val()) {
            Swal.fire({
                title: 'Số tín chỉ còn trống',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return false
        }
        return true
    }
    btnLuu_click = async () => {

        if (!this.validateForm()) {
            return
        }
        let dataApiSave = {
            maMonHoc : $('#idMaMonHoc').val(),
            tenMonHoc : $('#idTenMonHoc').val(),
            soTinChi : $('#idSoTinChi').val()
        }
        console.log(dataApiSave)
        let {data : response} = await axios.post('/java05/monhoc-api/postSaveMonHoc', dataApiSave)
        if (!response.success) {
            Swal.fire({
                title: response.message,
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return
        }
        Swal.fire({
            title: response.message,
            icon: 'success',
            showConfirmButton: false,
            timer: 1500
        })
        this.getListMonHoc()
    }
    btnXoa_click = async () => {
        if (!$('#idMaMonHoc').val()) {
            Swal.fire({
                title: 'Chưa có mã môn học',
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
        }).then(async (result) => {
            if (!result.isConfirmed) {
                Swal.fire({
                    title: 'Chưa xóa môn học',
                    icon: 'success',
                    showConfirmButton: false,
                    timer: 1500
                })
                return
            }
            let param = {
                maMonHoc : $('#idMaMonHoc').val()
            }
            let {data: response} = await axios.delete('/java05/monhoc-api/deleteByMaMonHoc', {params: param})
            if (!response.success) {
                Swal.fire({
                    title: response.message,
                    icon: 'error',
                    showConfirmButton: false,
                    timer: 1500
                })
                return
            }
            Swal.fire({
                title: response.message,
                icon: 'success',
                showConfirmButton: false,
                timer: 1500
            })
            await this.getListMonHoc();
        });
    }
}