class ThongKe {
    listMonHoc = []
    listHocKy =[]
    dataPieChart= {}
    dataColumnChart = {}
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
        this.dataColumnChart = {
            diem0 : response.data.soLuongDiem0,
            diem1: 2
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
                    label: 'My First Dataset',
                    data: Object.values(this.dataPieChart),
                    backgroundColor: [
                        'rgb(255, 99, 132)',
                        'rgb(54, 162, 235)',
                        'rgb(255, 205, 86)'
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
            labels: Object.keys(this.dataPieChart),
            datasets: [{
                label: 'Phổ điểm',
                data: Object.values(this.dataPieChart),
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(255, 159, 64, 0.2)',
                    'rgba(255, 205, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(201, 203, 207, 0.2)'
                ],
                borderColor: [
                    'rgb(255, 99, 132)',
                    'rgb(255, 159, 64)',
                    'rgb(255, 205, 86)',
                    'rgb(75, 192, 192)',
                    'rgb(54, 162, 235)',
                    'rgb(153, 102, 255)',
                    'rgb(201, 203, 207)'
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