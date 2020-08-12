<template>
    <div>
        <div class="st_page">
            <div class="sf_top">

            </div>

            <el-row :gutter="20">
                <el-col :span="4">
                    <div class="row11">
                        <p>投资计划</p>
                        <h2>2000KM 1560万KVA</h2>
                        <div id="set11" style="height:100px;width:300px;"/>
                    </div>
                </el-col>
                <el-col :span="4">
                    <el-col :span="4">
                        <div class="row11">
                            <p>投资计划</p>
                            <h2>2000KM 1560万KVA</h2>
                            <div id="set12" style="height:100px;width:300px;"/>
                        </div>
                    </el-col>

                </el-col>
                <el-col :span="4">
                    <el-col :span="4">
                        <div class="row11">
                            <p>投资计划</p>
                            <h2>2000KM 1560万KVA</h2>
                            <div id="set13" style="height:100px;width:300px;"/>
                        </div>
                    </el-col>

                </el-col>
                <el-col :span="4">
                    <div class="row11">
                        <p>投资计划</p>
                        <h2>2000KM 1560万KVA</h2>
                        <div id="set21" style="height:100px;width:300px;"></div>
                    </div>

                </el-col>
                <el-col :span="4">
                    <div class="row11">
                        <p>投资计划</p>
                        <h2>2000KM 1560万KVA</h2>
                        <div id="set22" style="height:100px;width:300px;"></div>
                    </div>

                </el-col>
            </el-row>

            <el-row>
                <div class="row21">
                    <div id="set3" style="height: 100px;width: 100px"></div>
                </div>
            </el-row>

            <el-row :gutter="20">
                <el-col :span="11">
                    <div class="table" style="width: 800px">
                        <el-table
                                :data="tableData"
                                style="width: 100%">
                            <el-table-column
                                    prop="v1"
                                    label="单位名称"
                                    width="180">
                            </el-table-column>
                            <el-table-column
                                    prop="v2"
                                    label="投资计划"
                                    width="180">
                            </el-table-column>
                            <el-table-column
                                    prop="v3"
                                    label="开工线路">
                            </el-table-column>
                            <el-table-column
                                    prop="v4"
                                    label="投产线路">
                            </el-table-column>
                            <el-table-column
                                    prop="v5"
                                    label="投产变电">
                            </el-table-column>
                        </el-table>
                        <div class="block">
                            <el-pagination
                                    @size-change="handleSizeChange"
                                    @current-change="handleCurrentChange"
                                    :current-page="currentPage4"
                                    :page-sizes="[100, 200, 300, 400]"
                                    :page-size="100"
                                    layout="total, sizes, prev, pager, next, jumper"
                                    :total="400">
                            </el-pagination>
                        </div>
                    </div>
                </el-col>
                <el-col :span="11">
                    <div class="plan">
                        <div id="set4" style="height: 300px; width: 300px"></div>
                    </div>
                </el-col>
            </el-row>

        </div>

    </div>

</template>

<script>
    export default {
        name: "StaticPage",
        data() {
            return {
                currentPage1: 5,
                currentPage2: 5,
                currentPage3: 5,
                currentPage4: 4,
                tableData: [
                    {
                        v1: '国网长沙...',
                        v2: '2,234',
                        v3: '2,234',
                        v4: '2,234',
                        v5: '2,234',
                    },
                    {
                        v1: '国网株洲...',
                        v2: '2,234',
                        v3: '2,234',
                        v4: '2,234',
                        v5: '2,234',
                    },
                    {
                        v1: '国网湘潭...',
                        v2: '2,234',
                        v3: '2,234',
                        v4: '2,234',
                        v5: '2,234',
                    },
                    {
                        v1: '国网衡阳...',
                        v2: '2,234',
                        v3: '2,234',
                        v4: '2,234',
                        v5: '2,234',
                    },
                    {
                        v1: '国家昭阳...',
                        v2: '2,234',
                        v3: '2,234',
                        v4: '2,234',
                        v5: '2,234',
                    },
                ]
            }
        },
        methods:{
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
            },
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
            },
            set1(id){
                let myChart = this.$echarts.init(document.getElementById(id));
                let option = {
                    xAxis: {
                        show:false,
                        type: 'category',
                        boundaryGap: false,
                        data: ['1', '2', '3', '4', '5', '6', '7','8','9',`11`,'10','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26']
                    },
                    yAxis: {
                        show:false,
                        type: 'value'
                    },

                    series: [
                        {
                            symbol: "none",
                            smooth:true,
                            data: [2,1,5.5,5,10,
                                2.5,3.5,4.5,4.5,4,
                                1,1,3.5,4.5,6.5,
                                10,5,3.5,4.5,1,
                                1,1,1,2,0],
                            type: 'line',
                            areaStyle: {
                                normal: {
                                    color: 'rgb(151,95,228)' //改变区域颜色
                                }
                            },
                            itemStyle: {
                                normal: {
                                    color: 'rgb(151,95,228)', //改变折线点的颜色
                                    lineStyle: {
                                        color: 'rgb(151,95,228)' //改变折线颜色
                                    }
                                }
                            },
                        }]
                };
                myChart.setOption(option);

            },
            set2(id){
                let myChart = this.$echarts.init(document.getElementById(id));
                let option = {
                    color: ['#3398DB'],
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: [
                        {
                            show:false,
                            type: 'category',
                            data: ['1', '2', '3', '4', '5', '6', '7','8','9','10','11','12','13','14','15','16','17'],
                            axisTick: {
                                alignWithLabel: true
                            }
                        }
                    ],
                    yAxis: [

                        {
                            show:false,
                            type: 'value'
                        }
                    ],
                    series: [
                        {

                            name: '直接访问',
                            type: 'bar',
                            barWidth: '60%',
                            data: [10,6,4,5,2,3,4,12,2,4,0,3,4,6,4,5,8]
                        }
                    ]
                };
                myChart.setOption(option);
            },

            set3(id) {
                let myChart = this.$echarts.init(document.getElementById(id));
                let option = {

                    series: [
                        {
                            name: '访问来源',
                            type: 'pie',
                            radius: ['50%', '70%'],
                            avoidLabelOverlap: false,
                            label: {
                                show: false,
                                position: 'center'

                            },
                            color: ['rgb(153,213,253)', 'rgb(233,233,233)'],
                            emphasis: {
                                label: {
                                    show: true,
                                    fontSize: '30',
                                    fontWeight: 'bold'
                                }
                            },
                            labelLine: {
                                show: false
                            },
                            data: [
                                {value: 80, name: '直接访问'},
                                {value: 20, name: '邮件营销'},

                            ]
                        }
                    ]
                };
                myChart.setOption(option);
            },
            set4(id){
                let myChart = this.$echarts.init(document.getElementById(id));
                let option = {
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b}: {c} ({d}%)'
                    },
                    legend: {
                        orient: 'vertical',
                        right: 10,
                        icon:"circle",
                        data: ['10kV及以下','1000kV','500kV','110kV','220kV','其他'],
                        width:20
                    },
                    graphic:{       //图形中间文字
                        type:"text",
                        left:"center",
                        top:"center",
                        style:{
                            text:"总投资\n\n￥ 166.00",
                            textAlign:"center",
                            fontSize:30
                        }
                    },
                    series: [
                        {
                            name: '访问来源',
                            type: 'pie',
                            radius: ['50%', '70%'],
                            avoidLabelOverlap: false,
                            label: {
                                show: false,
                                position: 'center'
                            },
                            emphasis: {
                                label: {
                                    show: false,
                                    fontSize: '30',
                                    fontWeight: 'bold'
                                }
                            },
                            labelLine: {
                                show: false
                            },
                            color:['rgb(153,213,253)', 'rgb(54,203,203)','rgb(77,203,115)','rgb(250,211,55)','rgb(242,99,123)','rgb(151,95,228)'],
                            data: [
                                {value: 25, name: '10kV及以下'},
                                {value: 25, name: '1000kV'},
                                {value: 10, name: '500kV'},
                                {value: 15, name: '110kV'},
                                {value: 13, name: '220kV'},
                                {value:12,name:'其他'}
                            ],
                            itemStyle:{
                                normal: {
                                    borderWidth: 5,
                                    borderColor: '#fff',
                                }
                            }
                        }
                    ]
                };
                myChart.setOption(option);

            },
            inits(){
                this.set1("set11");
                this.set1("set12");
                this.set1("set13");
                this.set2("set21");
                this.set2("set22");
                this.set3("set3");
                this.set4("set4");
            },
        },
        mounted:function(){
            this.inits();//需要触发的函数
        }

    }

</script>

<style scoped>
    .st_page{
        background-color: rgb(240,242,245);
        width: 100%;
        height: 100%;
    }
    .sf_top{
        height: 30px;
        width: 100%;
        border: red 1px solid;
    }
    .row11{
        background-color: white;
        height: 200px;
        width: 300px;
        margin: 5px;
    }
    .plan{
        background-color: white;
    }
</style>