class LichHoc {
    listHocKy = [];
    loadInit = () => {
        this.getListHocKy();
    }

    getListHocKy = async () => {
        let {data : response} = await axios.get('/java05/hocki-api/getAllHocKy')
        if (!response.success) {
            Swal.fire({
                title: response.messages,
                icon:'error',
                showConfirmButton:false,
                timer:1500
            })
            return
        }
        this.listHocKy =response.data.map(e=>({
            maHk : e.maHk,
            tenHocKi : e.tenHocKi,
            ngayBatDau : e.ngayBatDau,
            ngayKetThuc : e.ngayKetThuc
        }))
        this.createTableLichHoc()
    }

    createTableLichHoc = () => {
        let header = `<table class="table table-responsive table-bordered table-hover border-black" id="tableLichHoc">
                                <thead class="text-center">
                                    <th>No.</th>
                                    <th>Mã học kỳ</th>
                                    <th>Học kỳ</th>
                                    <th>Ngày bắt đầu</th>
                                    <th>Ngày kết thúc</th>
                                </thead>
                                <tbody class="tableSinhVien">`
        let body = ``
        this.listHocKy.forEach((e, index) => {
            body +=   `         <tr>
                                   <td class="bg-transparent text-center">${index + 1}</td>
                                   <td class="bg-transparent text-center">${e.maHk}</td>  
                                   <td class="bg-transparent text-center">${e.tenHocKi}</td> 
                                   <td class="bg-transparent text-center">${e.ngayBatDau}</td> 
                                   <td class="bg-transparent text-center">${e.ngayKetThuc}</td> 
                               </tr>`
        })
        let footer = `</tbody></table>`
        let result = header + body + footer
        $('.tableLichHoc').html(result);
        let table = new DataTable('#tableLichHoc', {
            searching: false,
            info: false,
            paging: true,
            ordering: false,
            lengthMenu: [10]
        });
        $('.dt-length').hide()
        let safe = this;
        table.on('dblclick', 'tbody tr', function () {
            let data = table.row(this).data();
            safe.fillDataToForm({
                maHk: data[1],         // cột 1: maHk.
                tenHocKi: data[2],     // cột 2: Học kỳ
                ngayBatDau: data[3],   // cột 3: Ngày bắt đầu
                ngayKetThuc: data[4]   // cột 4: Ngày kết thúc
            });
        });
        this.fillDataToForm(this.listHocKy[0])
    }

    fillDataToForm = (hocKi) => {
        $('#idMaHocKy').val(hocKi.maHk)
        $('#idTenHocKy').val(hocKi.tenHocKi)
        $('#idNgayBatDau').val(hocKi.ngayBatDau)
        $('#idNgayKetThuc').val(hocKi.ngayKetThuc)
    }

    btnLuu_click = async () => {
        if (!this.validateForm()) {
            return
        }
        let dataApiSave = {
            maHk : $('#idMaHocKy').val(),
            tenHocKi : $('#idTenHocKy').val(),
            ngayBatDau : $('#idNgayBatDau').val(),
            ngayKetThuc : $('#idNgayKetThuc').val()
        }
        let {data : response} = await axios.post('/java05/hocki-api/saveHocKi', dataApiSave)
        if (!response.success) {
            Swal.fire({
                title: response.message,
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return
        }
        if (response.data == null ) {
            Swal.fire({
                title: "Save thất bại",
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
        this.getListHocKy();
    }

    btnXoa_click = async () => {
        if (!$('#idMaHocKy').val()) {
            Swal.fire({
                title: 'Chưa có mã học kỳ',
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
                    title: 'Chưa xóa học kỳ',
                    icon: 'success',
                    showConfirmButton: false,
                    timer: 1500
                })
                return
            }
            let param = {
                maHk : $('#idMaHocKy').val()
            }
            let {data: response} = await axios.delete('/java05/hocki-api/deleteHocKyByMaHocKy', {params: param})
            if (!response.success) {
                Swal.fire({
                    title: response.message,
                    icon: 'error',
                    showConfirmButton: false,
                    timer: 1500
                })
                return
            }else if (response.data == 0) {
                Swal.fire({
                    title: "Xóa học kì thất bại",
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
            await this.getListHocKy();

        });
    }

    btnClearForm_click = () => {
        $('#idMaHocKy').val('')
        $('#idTenHocKy').val('')
        $('#idNgayBatDau').val('')
        $('#idNgayKetThuc').val('')
        $('#idXacNhan').prop('checked', false)
        this.verifyForm()
    }

    getListHocKiByTime = async () => {
        let param = {
            time : $('#idFilterNgay').val()
        }
        let {data: response} = await axios.get("/java05/hocki-api/getHocKyByTime",{
            params: param
        })
        if (!response.success) {
            Swal.fire({
                title: response.message,
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return
        }
        this.listHocKy = response.data.map(e => ({
            maHk : e.maHk,
            tenHocKi : e.tenHocKi,
            ngayBatDau : e.ngayBatDau,
            ngayKetThuc : e.ngayKetThuc
        }))
        this.createTableLichHoc()
    }

    reset_filter = () => {
        $('#idFilterNgay').val('');
        this.getListHocKy();
    }

    verifyForm = () => {
        let value = $('#idXacNhan').prop('checked')
        $('#idBtnLuu').prop('disabled', !value)
        $('#idBtnXoa').prop('disabled', !value)
        $('#idBtnCapNhat').prop('disabled', !value)

        $('#idMaHocKy').prop('disabled', value)
        $('#idTenHocKy').prop('disabled', value)
        $('#idNgayBatDau').prop('disabled', value)
        $('#idNgayKetThuc').prop('disabled', value)
    }

    validateForm = () => {
        if (!$('#idMaHocKy').val()) {
            Swal.fire({
                title: 'Mã Học Kỳ còn trống',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return false
        }
        if (!$('#idTenHocKy').val()) {
            Swal.fire({
                title: 'Tên học kỳ còn trống',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return false
        }
        if (!$('#idNgayBatDau').val()) {
            Swal.fire({
                title: 'Ngày bắt đầu còn trống',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return false
        }
        if (!$('#idNgayKetThuc').val()) {
            Swal.fire({
                title: 'Ngày kết thúc còn trống',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return false
        }
        return true
    }
}