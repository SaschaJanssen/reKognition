var Drawer = (function () {

    var canvas = document.getElementById("videoCan")

    function drawRect (context, person) {

        var leftX = context.canvas.width * person.boundingBox.left
        var leftY = context.canvas.height * person.boundingBox.top
        var width = context.canvas.width * person.boundingBox.width
        var height = context.canvas.height * person.boundingBox.height

        var ageText = "Age " + person.ageRange.low
        var gender = person.gender.value

        context.beginPath()
        context.fillStyle = "#ffffff"
        context.fillRect(leftX + 5, leftY + height + 2, context.measureText(ageText).width + 10, 14)
        context.fillRect(leftX + 5, leftY + height + 15, context.measureText(gender).width + 10, 14)

        context.fillStyle = 'red'
        context.font = '10pt Arial'
        context.fillText("Age " + person.ageRange.low, leftX + 5, leftY + height + 14)

        context.fillText(gender, leftX + 5, leftY + height + 28)

        context.stroke()


        context.beginPath()
        context.lineWidth = "1"
        context.strokeStyle = "yellow"

        context.rect(leftX, leftY, width, height)
        context.stroke()

    }

    return {

        drawVideo: function () {
            var context = canvas.getContext('2d')
            var faceData = Analyser.getFaceData()

            context.beginPath()

            context.clearRect(0, 0, 640, 480)
            context.drawImage(video, 0, 0, 640, 480)

            if ((faceData === undefined) || (faceData == null)) return
            $.each(faceData, function () {
                drawRect(context, this)
            })
        }

    }

})();
