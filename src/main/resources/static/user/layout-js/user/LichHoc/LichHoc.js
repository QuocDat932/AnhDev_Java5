class LichHoc {
    listHocKy = [{
        maHocKy : 'e.maHocKy',
        tenHocKy : 'e.tenHocKy',
        ngayBatDau : 'e.ngayBatDau',
        ngayKetThuc: 'e.ngayKetThuc'
    }]
    loadInit = () => {

    }
    getListHocKy = async () => {
        let param = {
            maHocKy : $('#idFilterNgay').val()
        }
        let {data : response} = await axios.get('', {
            params : param
        })
        if (!response.success) {

        }
    }
}