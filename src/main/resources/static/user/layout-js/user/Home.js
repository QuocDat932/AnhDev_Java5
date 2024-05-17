class Home {
    loadInit = () => {
        this.generateCalendar()
    }
    generateCalendar = () => {
        let calendar = $('#calendar')
        let currentDate = new Date()
        let month = currentDate.getMonth()
        let year = currentDate.getFullYear()

        let firstDayOfMonth = new Date(year, month, 1)
        let lastDayOfMonth = new Date(year, month + 1, 0)

        let firstDayOfWeek = firstDayOfMonth.getDay()
        let totalDays = lastDayOfMonth.getDate()

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
    showAddTaskModal = () => {
        let modal = $('#addTaskModal')
        modal.style.display = 'block'
    }
    closeAddTaskModal = () => {
        let modal = $('#addTaskModal')
        modal.style.display = 'none'
    }
    deleteTask = (taskElement) => {

    }
    postSaveTask = () => {

    }
}