<template>
    <div>
        <div class="st_page">
            <el-row>
                <div style="background-color:#fff;height: 50px">
                    <el-form :inline="true" style="padding-top: 10px">
                        <el-form-item label="单位:" style="margin-left: 50px">
                            <el-select v-model="unit"
                                       placeholder="请选择"
                                       style="width: 200px">
                                <el-option
                                        v-for="item in this.unitList"
                                        :key="item.value"
                                        :label="item.lable"
                                        :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-button style="background-color:rgb(49,112,103);color: white">查询</el-button>
                        <el-button>提交审核</el-button>
                        <el-button>退回</el-button>
                    </el-form>
                </div>
            </el-row>

            <el-row type="flex" justify="center">
                <el-col :span="5">
                    <div class="row11">
                        <span  style="font-size: 15px;color:#909399;" >投资计划</span>
                        <p  style="font-size: 30px; text-align: center" >126,560万元 10000个</p>
                        <div id="set11"  class="line_chart"/>
                        <p style="font-size: 15px;color: #606266;padding: 0px 10% 2% 10%">主网65600 城网60000 农网60000</p>
                    </div>
                </el-col>
                <el-col :span="5">
                    <el-col :span="4">
                        <div class="row11">
                            <span style="font-size: 15px;color:#909399;">新开工</span>
                            <p  style="font-size: 30px; text-align: center" >2000万元 7000个</p>
                            <div id="set12" class="line_chart"/>
                            <p style="color: #606266;padding: 0px 10% 2% 10%">占年度计划比例  70%</p>
                        </div>
                    </el-col>

                </el-col>
                <el-col :span="5">
                    <el-col :span="4">
                        <div class="row11">
                            <span style="font-size: 15px;color:#909399;">续建</span>
                            <p  style="font-size: 30px; text-align: center" >6560万元 3000个</p>
                            <div id="set13" class="line_chart"/>
                            <p style="color: #606266;padding: 0px 10% 2% 10%">占年度计划比例  30%</p>
                        </div>
                    </el-col>
                </el-col>
                <el-col :span="5">
                    <div class="row11">
                        <span style="font-size: 15px;color:#909399;">新开工里程碑</span>
                        <p  style="font-size: 30px; text-align: center" >2000KM 1560万KVA</p>
                        <div id="set21" class="Histogram"></div>
                        <p style="color: #606266;padding: 0px 10% 2% 10%">
                            <span>必开<i class="el-icon-caret-top" style="color: rgb(150,220,116)"/> 85%  </span>
                            <span style="float:right;">争开<i class="el-icon-caret-bottom" style="color: red"/>15%</span>
                        </p>
                    </div>
                </el-col>
                <el-col :span="5">
                    <div class="row11">
                        <span style="font-size: 15px;color:#909399;">投产里程碑</span>
                        <p  style="font-size: 30px; text-align: center" >1000KM 750万KVA</p>
                        <div id="set22" class="Histogram"></div>
                        <p style="color: #606266;padding: 0px 10% 2% 10%">项目个数  300</p>
                    </div>
                </el-col>
            </el-row>

            <el-row >
                <template>
                    <el-carousel :autoplay="false" arrow="always" indicator-position="none" height="170px" style="background-color: white;">
                        <el-carousel-item>
                            <el-col :span="22" :offset="1" style="border-left:1px rgb(235,235,235) solid;border-right: 1px rgb(235,235,235) solid ">
                                <el-col :span="4">
                                    <el-col :span="2" style="width: 100px;">
                                        <p style="font-size: 20px;color:rgb(112,112,113);">三区两州</p>
                                        <p style="font-size: 15px;color:#909399;">投资</p>
                                        <p style="font-size: 25px;color: rgba(0,0,0,0.6)">3320</p>
                                    </el-col>
                                    <el-col :span="2">
                                        <div id="set31" style="height: 170px;width: 170px;"></div>
                                    </el-col>
                                </el-col>

                                <el-col :span="4">
                                    <el-col :span="2" style="width: 100px;">
                                        <p style="font-size: 20px;color:rgb(112,112,113);">煤改电</p>
                                        <p style="font-size: 15px;color:#909399;">投资</p>
                                        <p style="font-size: 25px;color: rgba(0,0,0,0.6)">3320</p>
                                    </el-col>
                                    <el-col :span="2">
                                        <div id="set36" style="height: 170px;width: 170px;"></div>
                                    </el-col>
                                </el-col>
                                <el-col :span="4">
                                    <el-col :span="2" style="width: 100px;">
                                        <p style="font-size: 20px;color:rgb(112,112,113);">业扩配套</p>
                                        <p style="font-size: 15px;color:#909399;">投资</p>
                                        <p style="font-size: 25px;color: rgba(0,0,0,0.6)">3320</p>
                                    </el-col>
                                    <el-col :span="2">
                                        <div id="set32" style="height: 170px;width: 170px;"></div>
                                    </el-col>
                                </el-col>
                                <el-col :span="4">
                                    <el-col :span="2" style="width: 100px;">
                                        <p style="font-size: 20px;color:rgb(112,112,113);">边防供电</p>
                                        <p style="font-size: 15px;color:#909399;">投资</p>
                                        <p style="font-size: 25px;color: rgba(0,0,0,0.6)">3320</p>
                                    </el-col>
                                    <el-col :span="2">
                                        <div id="set33" style="height: 170px;width: 170px;"></div>
                                    </el-col>
                                </el-col>
                                <el-col :span="4">
                                    <el-col :span="2" style="width: 100px;">
                                        <p style="font-size: 20px;color:rgb(112,112,113);">抵边村寨</p>
                                        <p style="font-size: 15px;color:#909399;">投资</p>
                                        <p style="font-size: 25px;color: rgba(0,0,0,0.6)">3320</p>
                                    </el-col>
                                    <el-col :span="2">
                                        <div id="set34" style="height: 170px;width: 170px;"></div>
                                    </el-col>
                                </el-col>
                                <el-col :span="4">
                                    <el-col :span="2" style="width: 100px;">
                                        <p style="font-size: 20px;color:rgb(112,112,113);">阳光扶贫</p>
                                        <p style="font-size: 15px;color:#909399;">投资</p>
                                        <p style="font-size: 25px;color: rgba(0,0,0,0.6)">3320</p>
                                    </el-col>
                                    <el-col :span="2">
                                        <div id="set35" style="height: 170px;width: 170px;"></div>
                                    </el-col>
                                </el-col>
                            </el-col>

                        </el-carousel-item>
                        <el-carousel-item>
                            <el-col :span="22" :offset="1" style="border-left:1px rgb(235,235,235) solid;border-right: 1px rgb(235,235,235) solid ">
                                <el-col :span="4">
                                    <el-col :span="2" style="width: 100px;">
                                        <p style="font-size: 20px;color:rgb(112,112,113);">三区两州</p>
                                        <p style="font-size: 15px;color:#909399;">投资</p>
                                        <p style="font-size: 25px;color: rgba(0,0,0,0.6)">3320</p>
                                    </el-col>
                                    <el-col :span="2">
                                        <div id="set311" style="height: 170px;width: 170px;"></div>
                                    </el-col>
                                </el-col>

                                <el-col :span="4">
                                    <el-col :span="2" style="width: 100px;">
                                        <p style="font-size: 20px;color:rgb(112,112,113);">煤改电</p>
                                        <p style="font-size: 15px;color:#909399;">投资</p>
                                        <p style="font-size: 25px;color: rgba(0,0,0,0.6)">3320</p>
                                    </el-col>
                                    <el-col :span="2">
                                        <div id="set361" style="height: 170px;width: 170px;"></div>
                                    </el-col>
                                </el-col>
                                <el-col :span="4">
                                    <el-col :span="2" style="width: 100px;">
                                        <p style="font-size: 20px;color:rgb(112,112,113);">业扩配套</p>
                                        <p style="font-size: 15px;color:#909399;">投资</p>
                                        <p style="font-size: 25px;color: rgba(0,0,0,0.6)">3320</p>
                                    </el-col>
                                    <el-col :span="2">
                                        <div id="set321" style="height: 170px;width: 170px;"></div>
                                    </el-col>
                                </el-col>
                                <el-col :span="4">
                                    <el-col :span="2" style="width: 100px;">
                                        <p style="font-size: 20px;color:rgb(112,112,113);">边防供电</p>
                                        <p style="font-size: 15px;color:#909399;">投资</p>
                                        <p style="font-size: 25px;color: rgba(0,0,0,0.6)">3320</p>
                                    </el-col>
                                    <el-col :span="2">
                                        <div id="set331" style="height: 170px;width: 170px;"></div>
                                    </el-col>
                                </el-col>
                                <el-col :span="4">
                                    <el-col :span="2" style="width: 100px;">
                                        <p style="font-size: 20px;color:rgb(112,112,113);">抵边村寨</p>
                                        <p style="font-size: 15px;color:#909399;">投资</p>
                                        <p style="font-size: 25px;color: rgba(0,0,0,0.6)">3320</p>
                                    </el-col>
                                    <el-col :span="2">
                                        <div id="set341" style="height: 170px;width: 170px;"></div>
                                    </el-col>
                                </el-col>
                                <el-col :span="4">
                                    <el-col :span="2" style="width: 100px;">
                                        <p style="font-size: 20px;color:rgb(112,112,113);">阳光扶贫</p>
                                        <p style="font-size: 15px;color:#909399;">投资</p>
                                        <p style="font-size: 25px;color: rgba(0,0,0,0.6)">3320</p>
                                    </el-col>
                                    <el-col :span="2">
                                        <div id="set351" style="height: 170px;width: 170px;"></div>
                                    </el-col>
                                </el-col>
                            </el-col>
                        </el-carousel-item>

                    </el-carousel>
                </template>
            </el-row>

            <el-row :gutter="20" >

                <el-col :span="12">
                    <div class="table" style="height: 324px;width: 100%;background-color: white;">
                        <div style="height: 50px;width: 100%;border-bottom: 1px rgb(237,237,237) solid">
                            <p style="font-weight: bold;">分单位投资计划</p>
                        </div>
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
                            <el-pagination style="background-color: white;text-align: right"
                                           background
                                           :small="true"
                                           @size-change="handleSizeChange"
                                           @current-change="handleCurrentChange"
                                           :current-page="currentPage4"
                                           :page-sizes="[5, 10, 20, 30]"
                                           :page-size="5"
                                           layout="  prev, pager, next, sizes,jumper"
                                           :total="25">
                            </el-pagination>
                        </div>
                    </div>
                </el-col>

                <el-col :span="12">

                    <div class="plan" style="height: 324px; background-color: white;">
                        <div style="height: 50px;width: 100%;border-bottom: 1px rgb(237,237,237) solid;padding-bottom: 10px">
                            <p style="font-weight: bold; ">分单位投资计划</p>
                        </div>
                        <div id="set4" style="height: 300px; width: 100%"/>
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
                unit: 1,
                unitList:[
                    {
                        lable:"国网湖南电力公司",
                        value:1,
                    },
                    {
                        lable:"国网湖北电力公司",
                        value:2,
                    }
                ],
                ss:["set31","set32","set33","set34","set35","set36"],
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
                ],
                datalist4:[
                    {value: 25, name: '10kV及以下'},
                    {value: 25, name: '1000kV'},
                    {value: 9, name: '500kV'},
                    {value: 16, name: '110kV'},
                    {value: 10, name: '220kV'},
                    {value:15,name:'其他',selected:true}
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
                        data: ['1', '2', '3', '4', '5', '6', '7','8','9',`11`,'10','12','13',
                            '14','15','16','17','18','19','20','21','22','23','24','25','26']
                    },
                    yAxis: {
                        show:false,
                        type: 'value'
                    },
                    grid: {
                        x: 10,
                        y: 0,
                        x2: 10,
                        y2: 20,
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
                        x: 10,
                        y: 0,
                        x2: 10,
                        y2: 20,
                    },
                    // grid: {
                    //     left: '3%',
                    //     right: '4%',
                    //     bottom: '3%',
                    //     containLabel: true
                    // },
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
                    legend: {
                        orient: 'vertical',
                        left: "40%",
                        icon:"circle",
                        data: ['10kV及以下','1000kV','500kV','110kV','220kV','其他'],
                        width:20,
                        itemGap: 30,
                        textStyle: {
                            fontSize: 13,
                            width: 160,
                            rich: {
                                a: {
                                    align: 'left',
                                    color: '#77899c',
                                    padding: [0, 0, 0, 10],
                                },
                                b: {
                                    align: 'right',
                                    color: '#eb3a53',
                                },
                                c: {
                                    align: 'right',
                                    color: 'red',
                                },
                            }
                        },
                        formatter: function (name) {
                            let arr = [
                                name,
                                "        ",
                                '{a|' + " | 10%" + '}',
                                "     ",
                                "￥4,544"
                            ];
                            return arr.join('');
                        }

                    },
                    title:{
                        text:"总投资",
                        left:"17%",
                        top:"40%",
                        textStyle:{
                            color: "rgb(144, 147, 153)",
                            fontWeight:"lighter",
                            fontSize:15,
                            align:"center"
                        }
                    },

                    graphic:{       //图形中间文字
                        type:"text",
                        left:"13%",
                        top:"50%",
                        style:{
                            text:"￥ 166.00",
                            textAlign:"center",
                            fontSize:30
                        }
                    },

                    series: [
                        {
                            name: '分单位投资计划',
                            type: 'pie',
                            radius: ['60%', '80%'],
                            center:['20%',"50%"],
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
                            data: this.datalist4,


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
            inits: function () {
                let _this = this;
                ["set11", "set12", "set13"].forEach((item) => {
                    _this.set1(item);
                });
                ['set21', 'set22'].forEach((item) => {
                    _this.set2(item);
                });
                ['set31', 'set32', 'set33', 'set34', 'set35',
                    'set36', 'set311', 'set321', 'set331', 'set341', 'set351', 'set361'].forEach((item) => {
                    _this.set3(item);
                });

                this.set4("set4");
            },
        },
        mounted:function(){
            this.inits();//需要触发的函数
        },
        beforeCreate() {
            document.querySelector('body').setAttribute('style','background-color:rgb(245,245,245)')
            },
        beforeDestroy(){
            document.querySelector('body').setAttribute('style', "background-color:''")
        }

    }

</script>

<style scoped>
    .el-row {
        margin-bottom: 5px;
    }
    .sf_top{
        height: 30px;
    }
    .el-carousel__item .Carousel{
        border-bottom: 1px solid #f1f4f8;
        display: block;
    }
    .row11{
        padding: 10px;
        background-color: white;
        height: 250px;
        width: 350px;
        margin: 5px;
        border: 2px rgb(235,235,235) solid;
        border-radius: 2px;
    }
    .row21{
    border:1px rgb(235,235,235);
    }
    .plan{
        background-color: white;
    }
    .line_chart{
        border-bottom: 1px solid rgb(238,238,238);
        width:350px;
        height:90px;
    }
    .Histogram{
        border-bottom: 1px solid rgb(238,238,238);
        width: 350px;
        height: 90px;
    }

</style>

