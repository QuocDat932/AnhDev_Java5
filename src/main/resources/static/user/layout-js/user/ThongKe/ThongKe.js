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
            Điểm0 : response.data.diem0,
            Điểm1 : response.data.diem1,
            Điểm2 : response.data.diem2,
            Điểm3 : response.data.diem3,
            Điểm4 : response.data.diem4,
            Điểm5 : response.data.diem5,
            Điểm6 : response.data.diem6,
            Điểm7 : response.data.diem7,
            Điểm8 : response.data.diem8,
            Điểm9 : response.data.diem9,
            Điểm10 : response.data.diem10,


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
                        'rgb(50,205,50)',
                        'rgb(255,215,0)'
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
                    'rgb(220,20,60)',
                    'rgb(255,69,0)',
                    'rgb(255,165,0)',
                    'rgba(0,255,255,0.93)',
                    'rgba(0,44,252,0.2)',
                    'rgb(0,255,0)',
                    'rgb(255,0,255)',
                    'rgb(187,10,72)',
                    'rgba(0,102,255,0.92)',
                    'rgb(206,0,252)'
                ],
                borderColor: [
                    'rgb(255, 99, 132)',
                    'rgb(255, 159, 64)',
                    'rgb(255, 205, 86)',
                    'rgb(75,143,192)',
                    'rgb(54, 162, 235)',
                    'rgb(82,246,18)',
                    'rgba(255,1,255,0.66)',
                    'rgba(187,10,72,0.63)',
                    'rgba(69,129,218,0.76)',
                    'rgba(206,82,238,0.66)'
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
                        beginAtZero: true
                    }
                }
            },
        };
        this.BarChart=new Chart(barChart,config)
    }

}