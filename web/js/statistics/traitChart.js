$.ajax({
    url: "/gvk/statisticsajax/gototraitChart",
    type: "GET",
    success: function (data) {
        option = {
            tooltip: {},
            title: {
                left: 'left',
                text: 'TOP 10 Traits',
            },
            grid:{
                bottom :100,
                left: 80,
            },
            yAxis: {
                name: 'Number of publications',
                nameLocation: "center",
                nameGap: 30,
                splitLine:{show: false},
                boundaryGap: [0, 0.01],
                nameTextStyle: {
                    fontSize: 15,
                    fontFamily: 'Arial',
                    fontWeight: 'bold'
                }
            },
            xAxis: {
                type: 'category',
                data: data.traitName,
                axisLabel:{
                    interval: 0,
                    rotate: 30,
                }
            },
            series: [
                {
                    name: 'Number of publications',
                    type: 'bar',
                    data:data.traitPubCount
                }
            ]
        };
        var myChart = echarts.init(document.getElementById('traitChart'));
        myChart.setOption(option);
    }
})