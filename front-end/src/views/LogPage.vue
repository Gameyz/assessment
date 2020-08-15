<template>
    <div>
        <div class="Log_page">
            <div class="log_fp">
                <el-form :inline="true" class="demo-form-inline" @submit.native.prevent>
                    <el-form-item label=时间>
                        <el-date-picker
                                @change="timeChange"
                                v-model="timeValue"
                                format="yyyy-MM-dd"
                                type="date"
                                range-separator="至"
                                start-placeholder="开始日期"
                                end-placeholder="结束日期">
                            <template slot-scope="scope">
                                <i class="el-icon-time"></i>
                                <span style="margin-left: 10px">{{ scope.row.date }}</span>
                            </template>
                        </el-date-picker>
                    </el-form-item>

                    <el-form-item label="日志等级">
                        <el-select v-model="levelValue" multiple placeholder="请选择" style="width: 320px"  @change="levelChange">
                            <el-option

                                    v-for="item in infoLevel"
                                    :key="item.value"
                                    :label="item.lable"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="IP地址">
                        <el-input v-model="IpValue"/>
                    </el-form-item>
                    <el-form-item label="端口" >
                        <el-input v-model="portValue" style="width: 100px"/>
                    </el-form-item>
                    <el-form-item label="类名">
                        <el-input v-model="classNameValue"/>
                    </el-form-item>
                    <el-form-item label="错误信息">
                        <el-input v-model="messageValue"/>
                    </el-form-item>
                    <el-button type="success" @click="select">查询</el-button>
                </el-form>

            </div>
            <div class="log_main">
                <el-table
                        :data="logTableData"
                        style="width: 100%"
                        :row-class-name="tableRowClassName">
                    <el-table-column style= "width:20%"
                            prop="@timestamp"
                            label="日期"
                            sortable
                            width="180"
                            column-key="date"
                            :filters="[{text: '2016-05-01', value: '2016-05-01'}, {text: '2016-05-02', value: '2016-05-02'}, {text: '2016-05-03', value: '2016-05-03'}, {text: '2016-05-04', value: '2016-05-04'}]"
                            :filter-method="filterHandler"
                    >
                    </el-table-column>
                    <el-table-column
                            prop="level"
                            label="日志等级"
                            width="180">
                        <template slot-scope="scope">
                            <el-tag v-if="scope.row.level==='DEBUG'" type="info">{{scope.row.level}}</el-tag>
                            <el-tag v-if="scope.row.level==='INFO'" type="success">{{scope.row.level}}</el-tag>
                            <el-tag v-if="scope.row.level==='WARN'" type="warning">{{scope.row.level}}</el-tag>
                            <el-tag v-if="scope.row.level==='ERROR'" type="danger">{{scope.row.level}}</el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column
                            prop="IpAndPort"
                            label="IP：端口">
                        <template slot-scope="scope">
                            {{scope.row.host}}:{{scope.row.port}}
                        </template>
                    </el-table-column>
                    <el-table-column
                            prop="logger_name"
                            label="类名">
                    </el-table-column>
                    <el-table-column
                            prop="message"
                            :show-overflow-tooltip="true"
                            label="信息">
                    </el-table-column>
                </el-table>
                <el-pagination
                        background
                        @size-change="handleSizeChange"
                        @current-change="handleCurrentChange"
                        :current-page="currentPage"
                        :page-sizes="[100, 200, 300, 500, 1000]"
                        :page-size="100"
                        layout="total, sizes, prev, pager, next, jumper"
                        :total=totalElements>
                </el-pagination>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "LogPage",
        data() {
            return{

                currentPage: 4,

                totalElements:5000,

                timeValue:new Date(),
                infoLevel:[
                    {
                        lable:"DEBUG",
                        value:"DEBUG"
                    },
                    {
                        lable:"INFO",
                        value:"INFO",
                    },
                    {
                        lable:"WARN",
                        value:"WARN",
                    },
                    {
                        lable:"ERROR",
                        value:"ERROR",
                    },

                ],
                levelValue:["WARN","ERROR"],
                IpValue:"127.0.0.1",
                portValue:"",
                classNameValue:"",
                messageValue:"",
                logTableData:[
                    // {
                    //     "@":"2016-05-02",
                    //     level:'INFO',
                    //     className:"com.zez.ass.test",
                    //     IpAndPort:"127.0.0.1:9500",
                    //     Message:"ddfgdfg",
                    // },
                    {
                        "@timestamp": "2020-08-14T03:49:55.272Z",
                        "@version": 1,
                        host: "127.0.0.1",
                        level: "INFO",
                        level_value: 20000,
                        logger_name: "com.zez.backend.controller.GlobalExceptionHandler",
                        message: "Failed execution",
                        port: 35254,
                        thread_name: "http-nio-8081-exec-10",
                    },
                    {
                        "@timestamp": "2020-08-14T03:49:55.272Z",
                        "@version": 1,
                        host: "127.0.0.1",
                        level: "ERROR",
                        level_value: 20000,
                        logger_name: "com.zez.backend.controller.GlobalExceptionHandler",
                        message: "Failed execution",
                        port: 35254,
                        thread_name: "http-nio-8081-exec-10",
                    },
                    {
                        "@timestamp": "2020-08-14T03:49:55.272Z",
                        "@version": 1,
                        host: "127.0.0.1",
                        level: "WARN",
                        level_value: 20000,
                        logger_name: "com.zez.backend.controller.GlobalExceptionHandler",
                        message: "Failed execution",
                        port: 35254,
                        thread_name: "http-nio-8081-exec-10",
                    },
                    {
                        "@timestamp": "2020-08-14T03:49:55.272Z",
                        "@version": 1,
                        host: "127.0.0.1",
                        level: "DEBUG",
                        level_value: 20000,
                        logger_name: "com.zez.backend.controller.GlobalExceptionHandler",
                        message: "Failed execution",
                        port: 35254,
                        thread_name: "http-nio-8081-exec-10",
                    },
                ],
                tableData:[
                    {
                        date: '2016-05-02',
                        name: '王小虎',
                        address: '上海市普陀区金沙江路 1518 弄',
                    }, {
                        date: '2016-05-04',
                        name: '王小虎',
                        address: '上海市普陀区金沙江路 1518 弄'
                    }, {
                        date: '2016-05-01',
                        name: '王小虎2',
                        address: '上海市普陀区金沙江路 1518 弄',
                    }, {
                        date: '2016-05-03',
                        name: '王小虎',
                        address: '上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄上海市普陀区金沙江路 1518 弄'
                    },
                ]
            }
        },
        methods:{
            levelChange(){
                console.log(this.levelValue);
            },
            timeChange(){
                console.log(this.timeValue);
            },
            tableRowClassName({row, rowIndex}) {
                if (row.level === "WARN") {
                    return 'warn-row';
                } else if(row.level==='INFO') {
                    return 'info-row';
                }else if(row.level==='DEBUG'){
                    return "debug-row";
                }else if(row.level==='ERROR'){
                    return "error-row";
                }
                return "";
            },
            filterHandler(value, row, column) {
                const property = column['property'];
                return row[property] === value;
            },
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
            },
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
            },
            init(){
                this.select();
            },
            select(){
                // @RequestParam("dateStart") Date dateStart,
                // @RequestParam("dateEnd")Date dateEnd,
                // @RequestParam("levels")List<LogLevel> levels,
                // @RequestParam("ip")String ip,
                // @RequestParam("port")Integer port,
                // @RequestParam("messages")String messages,
                // @RequestParam("className") String className,
                // @RequestParam(value = "page", defaultValue = "1") Integer page,
                // @RequestParam(value = "size",defaultValue = "20") Integer pageSize

                let arr ={params:{}};
                let _this = this;

                if(this.timeValue !==null && this.timeValue !== undefined){
                    arr.params["dateStart"] = this.timeValue;
                   // arr.params["dateEnd"] = this.timeValue[1];
                }

                arr["params"]["port"] = this.portValue;
                arr["params"]["messages"] = this.messageValue;
                arr["params"]["className"] = this.classNameValue;
                arr["params"]["levels"] = this.levelValue;
                arr["params"]["ip"] = this.IpValue;

                console.log(arr);
                axios.post("/log/select",arr.params)
                    .then(res=>{
                        console.log(res);
                        if(res.data.code === 200){
                            _this.logTableData = res.data.data.source;
                            _this.totalElements = res.data.data.totalElements;
                        }else {
                            console.log(res);
                        }

                        console.log(res.data.data.source);;
                    }).catch(err=>{
                        console.log(err);
                });
            },
            mounted:function(){
                this.init();//需要触发的函数
            }
        }
    }
</script>

<style>
    .el-table .error-row{
        background-color: #ff847f;

    }
    .el-table .warn-row {
        background: oldlace;
    }

    .el-table .info-row {
        background: #f0f9eb;
    }
    .el-table .debug-row{
        background-color: rgba(244,244,245,0.6);
    }
</style>