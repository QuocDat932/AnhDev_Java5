class BangDiem {
    listResult = []
    hasSysId = null

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

        console.log(response.data)

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

        console.log(response.data)

        for (const e of this.listResult) {
            let param = {
                mssv : e.mssv
            }

            let { data: response1 } = await axios.get('/java05/sinhvien-api/getSinhVienByMSSV', {params: param});
            if (response1.success) {
                e.hoVaTen = response1.data.hoVaTen; // Cập nhật tên sinh viên
            } else {
                e.hoVaTen = 'Không tìm thấy tên';
            }
        }

        this.createTableBangDiem()
    }

    createTableBangDiem = () => {
        // Hủy khởi tạo DataTable nếu nó đã tồn tại
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
        //Gán lại sự kiện double click cho hàng trong bảng
        table.off('dblclick').on('dblclick', 'tbody tr', function () {
            let data = table.row(this).index();
            selt.fillDataToForm(selt.listResult[data])
            selt.hasSysId = data+1;
            console.log(selt.hasSysId)
        });
        this.fillDataToForm(this.listResult[0])
    }

    getBangDiemByLichSuHocTapId = async (lichSuHocTap) => {
        let param = {
            sysId : lichSuHocTap.sysId,
            mssv : lichSuHocTap.mssv,
            lanThi : lichSuHocTap.lanThi
        }

        let {data : reponse} = await axios.get('/java05/lichsuhoctap-api/getLichSuHocTapByLichSuHocTapID',
            {params : param})
    }

    fillDataToForm = (bangDiem) => {
        $('#idMssv').val(bangDiem.mssv)
        $('#idHoTen').val(bangDiem.hoVaTen)
        $('#idMonHoc').val(bangDiem.monHoc)
        $('#idHocKy').val(bangDiem.hocKi)
        $('#idLanThi').val(bangDiem.lanThi)
        $('#idDiemThi').val(bangDiem.diemThi)
        $('#idKetQua').text(bangDiem.ketQua)
    }

    xuatKetQua = () =>{
        let inputDiem = $('#idDiemThi').val()

        if (inputDiem < 5 && inputDiem >= 0) {
            $('#idKetQua').text("False")
        } else if (inputDiem >=5 && inputDiem <= 10) {
            $('#idKetQua').text("Pass")
        } else {
            $('#idKetQua').text("Kết quả")
        }
    }

    clearForm = () =>{
        console.log(this.hasSysId)
        this.hasSysId = null
        console.log(this.hasSysId)
        this.fillDataToForm({})
        $('#idKetQua').text('Kết quả')
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
        // if (!this.validateForm()) {
        //     return
        // }

        let dataApiSave = {
            sysId : isUpdate ? this.hasSysId : '21',
            mssv : $('#idMssv').val(),
            lanThi : $('#idLanThi').val(),
            diemThi : $('#idDiemThi').val(),
            ketQua : $('#idKetQua').text(),
            monHoc : $('#idMonHoc').val(),
            hocKi : $('#idHocKy').val()
        }

        console.log(dataApiSave)

        let {data : response} = await axios.post('/java05/lichsuhoctap-api/saveLichSuHocTap',
            dataApiSave)

        console.log(response)

        if (isUpdate) {
            // let {dataUpdate : reponseUpdate} = await axios.put('/java05/sinhvien-api/updateSinhVien', dataApiSave)
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
}