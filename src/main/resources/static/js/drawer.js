var Drawer = (function () {

    var canvas = document.getElementById("videoCan")

    function drawRect(context, person) {

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

    function fillInfoTable(faceData) {

        $("#infotablebody tr").remove()

        $("#infotablebody")
            .append("<tr><th scope=\"row\">Gender</th><td>" + faceData.gender.value + "</td><td><span class=\"badge badge-primary badge-pill\">" + faceData.gender.confidence + "</span></td></tr>")
            .append("<tr><th scope=\"row\">Smile</th><td>" + faceData.smile.value + "</td><td><span class=\"badge badge-primary badge-pill\">" + faceData.smile.confidence + "</span></td></tr>")
            .append("<tr><th scope=\"row\">Eyeglasses</th><td>" + faceData.eyeglasses.value + "</td><td><span class=\"badge badge-primary badge-pill\">" + faceData.eyeglasses.confidence + "</span></td></tr>")
            .append("<tr><th scope=\"row\">sunglasses</th><td>" + faceData.sunglasses.value + "</td><td><span class=\"badge badge-primary badge-pill\">" + faceData.sunglasses.confidence + "</span></td></tr>")
            .append("<tr><th scope=\"row\">Beard</th><td>" + faceData.beard.value + "</td><td><span class=\"badge badge-primary badge-pill\">" + faceData.beard.confidence + "</span></td></tr>")
            .append("<tr><th scope=\"row\">Eyes open</th><td>" + faceData.eyesOpen.value + "</td><td><span class=\"badge badge-primary badge-pill\">" + faceData.eyesOpen.confidence + "</span></td></tr>")
            .append("<tr><th scope=\"row\">Mouth open</th><td>" + faceData.mouthOpen.value + "</td><td><span class=\"badge badge-primary badge-pill\">" + faceData.mouthOpen.confidence + "</span></td></tr>")

        if (faceData.emotions !== undefined && faceData.emotions != null) {
            $("#emotionlist li").remove()
            $.each(faceData.emotions, function () {
                $("#emotionlist").append("<li class=\"list-group-item d-flex justify-content-between align-items-center\">" +
                    this.type +
                    "<span class=\"badge badge-primary badge-pill\">" + this.confidence + "</span></li>")
            })
        }
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
                fillInfoTable(this)
            })
        }

    }

})();
