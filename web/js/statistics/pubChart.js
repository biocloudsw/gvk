$.ajax({
    url: "/gvk/statisticsajax/gotoPubChartajax",
    type: "GET",
    success: function (data) {
        var myChart = echarts.init(document.getElementById('pubChart'));

        option = {
            tooltip: {},

            // toolbox: {
            //     show: true,
            //     feature: {
            //         saveAsImage: {
            //             show:true,
            //             pixelRatio: 8
            //         }
            //     }
            // },

            radius: '100%',
            title: {
                text: 'Number of Publications',
                left:'left'
            },

            grid:{
                x2:10,
                left: 80,
            },

            xAxis: {
                type: 'category',

                nameGap: 30,
                name: "Year",
                data: data.pubYear2,
                // axisLabel:{
                //     interval: 0,
                //     rotate: 30,
                //     textStyle: {
                //         "fontSize": 18
                //     }
                // }
            },

            yAxis: {
                type: 'value',
                nameLocation: "center",
                nameGap: 30,
                name: "Number of publications",
                splitLine:{show: true},
                nameTextStyle: {
                    fontSize: 15,
                    fontFamily: 'Arial',
                    fontWeight: 'bold'
                },
                // axisLabel : {
                //     fontSize: 18
                // }
            },
            series: [{
                data: data.yearCount,
                type: 'line',
                clickable : true,
                symbolSize: 10,
                label: {
                    show: true,
                    position: "top",
                    textStyle: {
                        "fontSize": 15
                    }
                }

            }]
        };


        myChart.setOption(option);

    }
})