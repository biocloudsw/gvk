$.ajax({
    url: "/gvk/statistics/pub",
    type: "GET",
    success: function (data) {
        var myChart = echarts.init(document.getElementById('pub'));

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
                data: data.year,
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
                data: data.year_count,
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