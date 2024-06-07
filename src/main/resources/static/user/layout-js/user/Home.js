class Home {
    listTask = []
    currentTask = null
    currentDate = new Date()
    month = this.currentDate.getMonth() + 1
    year = this.currentDate.getFullYear()
    loadInit = async () => {
        this.generateCalendar()
        await this.getListTask()
    }
    getListTask = async (showMonth) => {
        let param = {
            sysIdUser: localStorage.getItem('sys_id'),
            month: this.month,
            year: this.year
        }
        let {data: response} = await axios.get('java05/ghiChu-api/getGhiChuBySysTdUser',
            {params: param})
        if (!response.success) {
            Swal.fire({
                title: response.message,
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            return
        }
        this.listTask = response.data.map(e =>
            ({
                id: e.sysId,
                name: e.tenGhiChu,
                date: e.ngay,
                description: e.noiDung,
                color: e.mauSac
            })
        )
        this.showTaskToCalendar()
    }
    generateCalendar = () => {
        $('#title-calendar').text(`Tháng ${this.month} - ${this.year}`)
        $('.calendar-grid').empty();
        let calendar = $('#calendar')

        let firstDayOfMonth = new Date(this.year, this.month - 1, 1)
        let lastDayOfMonth = new Date(this.year, this.month, 0)

        let firstDayOfWeek = firstDayOfMonth.getDay()
        let totalDays = lastDayOfMonth.getDate()
        for (let day = 1; day <= 7; day++) {
            if (day === 7) {
                let daySquare = $('<div>')
                daySquare.addClass('day-name fw-bold')
                daySquare.text(`Chủ nhật`)
                calendar.append(daySquare)
                break
            }
            let daySquare = $('<div>')
            daySquare.addClass('day-name fw-bold')
            daySquare.text(`Thứ ${day + 1}`)
            calendar.append(daySquare)
        }
        for (let i = 0; i < firstDayOfWeek; i++) {
            let blankDay = $('<div>')
            calendar.append(blankDay)
        }
        for (let day = 1; day <= totalDays; day++) {
            let daySquare = $('<div>')
            daySquare.addClass('calendar-day')
            daySquare.text(day)
            daySquare.attr('id', `day-${day}`);
            calendar.append(daySquare)
        }
    }
    showTaskToCalendar = () => {
        $('.task').remove();
        this.listTask.forEach((e, index) => {
            let taskDate = e.date.split('-')[2]
            let taskName = e.name
            let taskColor = e.color
            if (taskName && !isNaN(taskDate)) {
                let calendarDays = $('#calendar').children()
                for (let i = 0; i < calendarDays.length; i++) {
                    let day = calendarDays[i]
                    if (parseInt(day.outerText) === +taskDate) {
                        let taskElement = $('<div>')
                        taskElement.addClass('task')
                        taskElement.attr('id', `task-${index}`)
                        taskElement.text(taskName)
                        taskElement.css('background-color', taskColor)
                        taskElement.on('click', (e) => {
                            this.showTaskDetail(e)
                        })
                        $(day).append(taskElement)
                        break
                    }
                }
            }
        })
    }
    showTaskDetail = (element) => {
        let index = element.currentTarget.id.split('-')[1]
        this.currentTask = this.listTask[index]
        $('#taskColor').val(this.currentTask.color)
        $('#taskName').val(this.currentTask.name)
        $('#taskDate').val(this.currentTask.date)
        $('#taskDesc').val(this.currentTask.description)
    }
    deleteTask = async () => {
        if (!this.currentTask) {
            Swal.fire({
                title: 'Hãy chọn task cần xóa',
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
                    title: 'Vẫn giữ lại ghi chú',
                    icon: 'success',
                    showConfirmButton: false,
                    timer: 1500
                })
                return
            }
            let param = {
                sysId: this.currentTask.id,
            }
            let {data: response} = await axios.delete('java05/ghiChu-api/deleteGhiChu', {params: param})
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
            await this.getListTask()
        });
    }
    clearForm = () => {
        this.currentTask = null
        $('#taskColor').val('#111111')
        $('#taskName').val('')
        $('#taskDate').val('')
        $('#taskDesc').val('')
    }
    postSaveTask = async (isUpdate) => {
        if (!this.validateForm()) {
            return
        }
        let dataSaveTask = {
            sysId: isUpdate ? this.currentTask.id : null,
            tenGhiChu: $('#taskName').val(),
            noiDung: $('#taskDesc').val(),
            ngay: $('#taskDate').val(),
            mauSac: $('#taskColor').val(),
            sysIdUser: localStorage.getItem('sys_id')
        }
        let {data: response} = await axios.post('java05/ghiChu-api/postSaveGhiChu', dataSaveTask)
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
        await this.getListTask()
    }
    validateForm = () => {
        if (!$('#taskName').val()) {
            Swal.fire({
                title: 'Tên không được để trống',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            $('#taskName').focus()
            return false
        }
        if (!$('#taskDesc').val()) {
            Swal.fire({
                title: 'Nội dung không được để trống',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            $('#taskDesc').focus()
            return false
        }
        if (!$('#taskDate').val()) {
            Swal.fire({
                title: 'Ngày không được để trống',
                icon: 'error',
                showConfirmButton: false,
                timer: 1500
            })
            $('#taskDate').focus()
            return false
        }
        return true
    }
    btnNextMonth = async () => {
        ++this.month;
        if (this.month > 12) {
            ++this.year
            this.month = 1
        }
        $('#title-calendar').text(`Tháng ${this.month} - ${this.year}`)
        this.generateCalendar()
        await this.getListTask()
    }
    btnPrevMonth = async () => {
        --this.month
        if (this.month < 1) {
            --this.year
            this.month = 12
        }
        $('#title-calendar').text(`Tháng ${this.month} - ${this.year}`)
        this.generateCalendar()
        await this.getListTask()
    }
}