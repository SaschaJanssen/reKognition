var Analyser = (function () {

    var canvas = document.getElementById("videoCan")

    var processing = false
    var isStopped = true
    var faceData

    function analyseImage(imageData) {
        $.ajax({
            url: "/analyse/webcam",
            data: {imageData: imageData},
            type: "POST",
            dataType: "JSON"
        })
            .done(function (data) {
                faceData = data
                processing = false
            })
            .fail(function (e) {
                console.log(e)
            })
    }

    return {
        processImage: function () {
            if (processing || isStopped) return

            processing = true

            var imageData = canvas.toDataURL("image/jpeg", 1.0)
            analyseImage(imageData)
        },

        getFaceData: function () {
            return faceData
        },

        setStart: function () {
            isStopped = false
        },

        setStop: function () {
            isStopped = true
        }
    }

})()