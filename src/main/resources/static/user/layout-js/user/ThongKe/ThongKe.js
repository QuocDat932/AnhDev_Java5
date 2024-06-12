class ThongKe {
    listMonHoc = []
    listHocKy =[]
    dataPieChart= {}
    dataBarChart = {}
    chart = null
    BarChart = null

    loadInit = () => {
        this.getListMonHoc()
        this.getListHocKy()
    }

    getListMonHoc = async () => {
        let param = {
           monHoc: $('#selectMonHoc').val()
        }
        let { data: response } = await axios.get('/java05/monhoc-api/getAllMonHoc')
        if (!response.success) {
            Swal.fire({
                title: response.message,
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return
        }
        this.listMonHoc = response.data.map(e => ({
            maMonHoc: e.maMonHoc,
            tenMonHoc: e.tenMonHoc,
        }))

        this.createSelectMonHoc()
    }
    createSelectMonHoc = () => {
        let option = ``

        this.listMonHoc.forEach(monHoc => {
            option += `<option value="${monHoc.maMonHoc}">${monHoc.tenMonHoc}</option>`
        })
        $('#selectMonHoc').html(option)
    }
    getListHocKy = async () => {
        let param = {
            hocKy: $('#selectHocKy').val()
        }
        let { data: response } = await axios.get('/java05/hocki-api/getAllHocKy')
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
            maHocKy: e.maHk,
            tenHocKi: e.tenHocKi,
        }))

        this.createSelectHocKy()
    }
    createSelectHocKy = () => {
        let option = ``
        this.listHocKy.forEach(hocKy => {
            option += `<option value="${hocKy.maHocKy}">${hocKy.tenHocKi}</option>`
        })
        $('#selectHocKy').html(option)
    }
    getDataChart = async () => {
        let param = {
            maMonHoc: $('#selectMonHoc').val(),
            maHocKy: $('#selectHocKy').val()
        }
        let { data: response } = await axios.get('/java05/thongke-api/getPassFailResult', {params : param})
        if (!response.success) {
            Swal.fire({
                title: response.message,
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return
        }
        if (!response.data) {
            Swal.fire({
                title: 'Không tìm thấy dữ liệu',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return
        }
        this.dataPieChart = {
            Pass : response.data.tiLePass,
            Fail : response.data.tiLeFail
        }
        this.dataBarChart = {
            "Điểm 0" : response.data.diem0,
            "Điểm 1" : response.data.diem1,
            "Điểm 2" : response.data.diem2,
            "Điểm 3" : response.data.diem3,
            "Điểm 4" : response.data.diem4,
            "Điểm 5" : response.data.diem5,
            "Điểm 6" : response.data.diem6,
            "Điểm 7" : response.data.diem7,
            "Điểm 8" : response.data.diem8,
            "Điểm 9" : response.data.diem9,
            "Điểm 10" : response.data.diem10,


        }
        this.createBarChart()
        this.createPieChart()
    }
    createPieChart = () =>{
        let idChart = $('#myChart')
        if (this.chart) {
            this.chart.destroy()
        }
        this.chart = new Chart (idChart, {
            type: 'pie',
            data: {
                labels: Object.keys(this.dataPieChart)
                ,
                datasets: [{
                    label: 'ChartPassFailRates',
                    data: Object.values(this.dataPieChart),
                    backgroundColor: [
                        'rgb(140,238,76)',
                        'rgb(238,78,78)'
                    ],
                }]
            },
        });
    }
    createBarChart = () => {
        let barChart = $('#barChart')
        if (this.BarChart) {
            this.BarChart.destroy()
        }
        const data = {
            labels: Object.keys(this.dataBarChart),
            datasets: [{
                label: 'Phổ Điểm Chart',
                data: Object.values(this.dataBarChart),
                backgroundColor: [
                    'rgba(58, 190, 249, 0.8)',
                    'rgb(53, 114, 239, 0.8)'

                ],
                borderColor: [
                    'rgb(58, 190, 249)',
                    'rgb(53, 114, 239)'
                ],
                borderWidth: 1
            }]
        };
        const config = {
            type: 'bar',
            data: data,
            options: {
                scales: {
                    y: {
                        beginAtZero: true,
                        ticks:{
                            stepSize:1
                        }
                    }
                }
            },
        };
        this.BarChart=new Chart(barChart,config)
    }

}