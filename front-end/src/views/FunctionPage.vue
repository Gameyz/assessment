<template xmlns:el-col="http://www.w3.org/1999/html">
    <div id="functionPage">
        <div class="fp_header">
            <el-form :inline="true" class="demo-form-inline" style="width: 1400px" @submit.native.prevent>
                <el-form-item label="单位">
                    <el-cascader @change="unitChange()"
                                 expand-trigger="hover"
                                 :options="unit"
                                 v-model="unitValue"
                                 change-on-select
                                 :props="{ value: 'unitId',label: 'unitName',children: 'subUnits' }"
                                 style="width: 120px">
                    </el-cascader>
                </el-form-item>
                <el-form-item label="建设性质">
                    <el-select v-model="constructionNatureValue" placeholder="请选择" style="width: 120px"
                               @change="constructionChange()">
                        <el-option
                                v-for="item in constructionNature"
                                :key="item.constructionNatureId"
                                :label="item.constructionNatureName"
                                :value="item.constructionNatureId">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="年份">
                    <el-date-picker @change="yearChange()"
                                    style="width: 120px"
                                    v-model="yearValue"
                                    type="year"
                                    placeholder="选择日期"
                                    align="right">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="项目编码">
                    <el-input v-model="projectCode" style="width: 120px"/>
                </el-form-item>
                <el-form-item label="项目名称">
                    <el-input v-model="projectName" style="width: 120px"/>
                </el-form-item>
                <el-button type="success" @click="search">查询</el-button>
                <el-button type="success" @click="newProject">新增项目</el-button>
                <el-button type="success" @click="fixProject">修改项目</el-button>
                <el-button type="success" @click="saveData">保存</el-button>
            </el-form>
        </div>

        <div class="fp_main">
            <div class="table">
                <el-table
                        ref="singleTable"
                        :data="tableData"
                        style="width: 900px;"
                        :header-cell-style="getHeaderCellStyle"
                        :cell-style="getTableCellStyle"
                        highlight-current-row
                        @current-change="handleCurrentChange"
                        @row-click="rowClick"
                        v-loading=loadFalg
                        width="900px">

                    <el-table-column width="40px">
                        <template slot="header" slot-scope="scope">
                            <input type="checkbox">
                        </template>
                    </el-table-column>

                    <el-table-column prop="projectName" label="项目名称" width="260px"/>
                    <el-table-column prop="unitName" label="单位" width="230px"/>
                    <el-table-column prop="projectId" label="项目编码" width="120"/>
                    <el-table-column prop="constructionNatureName" label="建设性质" width="120"/>
                    <el-table-column prop="planValue" label="计划值" width="120">
                        <template slot-scope="scope">
                            <span v-show="rowBeingEdited!==scope.$index ||scope.$index===0"
                                  @dblclick="editLine(scope.$index)">
                                {{scope.row.planValue}}
                            </span>
                            <el-input
                                    autofocus="true"
                                    v-if="scope.$index!==0"
                                    v-show="rowBeingEdited===scope.$index"
                                    v-model="scope.row.planValue"
                                    :ref='"input"+scope.$index'
                                    @blur="tableIntPutLoseFocus(scope.$index)"
                            />
                        </template>
                    </el-table-column>

                </el-table>
            </div>
        </div>

        <div class="fp_footer">
            <div style="height: 45px;margin-left: 5px;margin-right: 5px">

                <select v-model="maxRowsNumOfTable"
                        placeholder="请选择"
                        @change="maxRowsChange()"
                        style="width: 40px;text-align: right; border: #98BAE4 solid 1px;">
                    <option value="10" label="10"/>
                    <option value="20" label="20"/>
                    <option value="50" label="50"/>
                </select>

                <span style="color: #D5D5D5; margin-left: 5px">|</span>

                <el-button type="text" @click="homePage">
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-home"></use>
                    </svg>
                </el-button>
                <el-button type="text" @click="previousPage">
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-break"></use>
                    </svg>
                </el-button>

                <span style="color: #D5D5D5">|</span>

                第<input v-model="pageNum"
                        style="width: 30px; border: #98BAE4 solid 1px;"
                        @blur="pageNumLoseFocus"
                        @keyup.enter="goPage"/>
                页，共{{totalPages}}页

                <span style="color: #D5D5D5">|</span>

                <el-button type="text" @click="nextPage">
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-up"></use>
                    </svg>
                </el-button>
                <el-button type="text" @click="endPage">
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-end"></use>
                    </svg>
                </el-button>

                <span style="color: #D5D5D5">|</span>
                <el-button type="text" style="margin-right: 5px" @click="refreshPage">
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-refresh"></use>
                    </svg>
                </el-button>
                <span style="float:right;">显示{{frontRecordingNumber}}到{{endRecordingNumber}},共{{totalRecordingNumber}}纪录</span>

            </div>
        </div>

        <div class="fp_dialog">
            <el-dialog title="新建项目" :visible.sync="newProjectDialogTableVisible">
                <el-table
                        border
                        :data="newProjectData"
                        :show-header="false">
                    <el-table-column label="条目" width="300px">
                        <template slot-scope="scope">
                            <span v-if="scope.$index===0">企业编码</span>
                            <span v-if="scope.$index===1" style="color: red">项目名称</span>
                            <span v-if="scope.$index===2" style="color: red">所属单位</span>
                            <span v-if="scope.$index===3">建设性质</span>
                        </template>
                    </el-table-column>

                    <el-table-column label="value" prop="value" width="600px">
                        <template slot-scope="scope">
                            <span v-if="scope.$index===0">{{newProjectData[0].value}}</span>
                            <span v-if="scope.$index===1"
                                  v-show="newProjectDataEdited!==scope.$index"
                                  @dblclick="editNewProjectLine(scope.$index)">{{newProjectData[1].value}}</span>
                            <el-input v-if="scope.$index===1"
                                      v-model="newProjectData[1].value "
                                      v-show="newProjectDataEdited===scope.$index"
                                      :ref='"newProjectInput"+scope.$index'
                                      @blur="newProjectloseFocus"
                            />

                            <el-cascader
                                    v-if="scope.$index===2"
                                    @change="unitChange()"
                                    expand-trigger="hover"
                                    :options="unit"
                                    v-model="newProjectData[2].value"
                                    change-on-select
                                    :props="{ value: 'unitId',label: 'unitName',children: 'subUnits' }"
                                    style="width: 120px">
                            </el-cascader>

                            <el-select v-if="scope.$index===3" v-model="newProjectData[3].value" placeholder="请选择" style="width: 120px">
                                <el-option
                                        v-for="item in constructionNature"
                                        :key="item.constructionNatureId"
                                        :label="item.constructionNatureName"
                                        :value="item.constructionNatureId">
                                </el-option>
                            </el-select>
                        </template>
                    </el-table-column>
                </el-table>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="newProjectDialogTableVisible = false">取 消</el-button>
                    <el-button type="primary" @click="addProject">确 定</el-button>
                </div>
            </el-dialog>
            <el-dialog title="维护项目" :visible.sync="fixProjectDialogTableVisible">
                <el-table
                        border
                        :data="fixProjectData"
                        :show-header="false">
                    <el-table-column label="条目" width="300px">
                        <template slot-scope="scope">
                            <span v-if="scope.$index===0">企业编码</span>
                            <span v-if="scope.$index===1" style="color: red">项目名称</span>
                            <span v-if="scope.$index===2" style="color: red">所属单位</span>
                            <span v-if="scope.$index===3">建设性质</span>
                        </template>
                    </el-table-column>

                    <el-table-column label="value" prop="value" width="600px">
                        <template slot-scope="scope">
                            <span v-if="scope.$index===0">{{fixProjectData[0].value}}</span>
                            <span v-if="scope.$index===1"
                                  v-show="fixProjectDataEdited!==scope.$index"
                                  @dblclick="editFixProjectLine(scope.$index)">{{fixProjectData[1].value}}</span>
                            <el-input v-if="scope.$index===1"
                                      v-model="fixProjectData[1].value "
                                      v-show="fixProjectDataEdited===scope.$index"
                                      :ref='"fixProjectInput"+scope.$index'
                                      @blur="fixProjectloseFocus"
                            />

                            <el-cascader
                                    v-if="scope.$index===2"
                                    @change="unitChange()"
                                    expand-trigger="hover"
                                    :options="unit"
                                    v-model="fixProjectData[2].value"
                                    change-on-select
                                    :props="{ value: 'unitId',label: 'unitName',children: 'subUnits' }"
                                    style="width: 120px">
                            </el-cascader>

                            <el-select v-if="scope.$index===3" v-model="fixProjectData[3].value" placeholder="请选择" style="width: 120px">
                                <el-option
                                        v-for="item in constructionNature"
                                        :key="item.constructionNatureId"
                                        :label="item.constructionNatureName"
                                        :value="item.constructionNatureId">
                                </el-option>
                            </el-select>
                        </template>
                    </el-table-column>
                </el-table>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="fixProjectDialogTableVisible = false">取 消</el-button>
                    <el-button type="primary" @click="fixProjectSubmit">确 定</el-button>
                </div>
            </el-dialog>

        </div>




    </div>
</template>

<script>
    export default {
        name: "FunctionPage",
        data() {
            return {

                //表头单位树形下拉框数据信息
                unit: [
                    {
                        unitId: 'all',
                        unitName: '全部',
                    },
                    {
                        unitId: 'jilin',
                        unitName: '吉林',
                        subUnits: [
                            {
                                unitId: 'changchun',
                                unitName: '长春',
                                upperLevelId:1
                            },
                            {
                                unitId: 'changchun',
                                unitName: '长春',
                                upperLevelId:1
                            },
                        ]
                    }
                ],
                //表头建设性质下拉框数据信息
                constructionNature: [
                    {
                        constructionNatureId: '1',
                        constructionNatureName: '全部',
                    }, {
                        constructionNatureId: 'xinjian',
                        constructionNatureName:'新建',
                    }, {
                        constructionNatureId: 'zaijian',
                        constructionNatureName: '在建',
                    }, {
                        constructionNatureId: 'jiancheng',
                        constructionNatureName: '建成',
                    },
                ],

                //表头数据绑定
                unitValue: -1,
                yearValue: null,
                projectCode: "",
                projectName: "",
                constructionNatureValue: 1,

                tableData: [
                    {
                        projectId:'',
                        projectName: "汇总",
                        unitId:null,
                        unitName: '',
                        constructionNatureId:null,
                        constructionNatureName: "",
                        planValue: 0,
                        modifyFlag:false,
                        oldPlanValue:0,
                    },
                ],
                rowBeingEdited: -1,

                //每页最大行数
                maxRowsNumOfTable: 10,

                //页面跳转输入框数据绑定
                pageNum: 1,
                nowPageNum: 1,

                //总页数
                totalPages: 1,

                //记录数
                frontRecordingNumber: 0,
                endRecordingNumber: 0,
                totalRecordingNumber: 0,


                newProjectData: [
                    {
                        value: 1,
                    },
                    {
                        value: "新项目名",
                    },
                    {
                        value: 1,
                    },
                    {
                        value: 1,
                    },
                ],
                newProjectDataEdited: -1,
                newProjectDialogTableVisible: false,

                currentRow: null,
                fixProjectData: [
                    {
                        value: 1,
                    },
                    {
                        value: 1,
                    },
                    {
                        value: [3,12],
                    },
                    {
                        value: 1,
                    },

                ],
                fixProjectDataEdited: -1,
                fixProjectDialogTableVisible: false,

                modifyFlag: false,
                loadFalg: false,

            };
        },
        methods: {
            /**
             * 修改表格头部单元格样式
             */
            getHeaderCellStyle() {
                return {
                    'background-color': '#7EBCBB',
                    'color': '#FFF',
                    'border-left': '1px #ABC2C1 solid',
                    'border-bottom': "none",
                    'text-align': 'center'
                }
            },

            /**
             * 设置表格单元格样式
             */
            getTableCellStyle({row, column, rowIndex, columnIndex}) {
                if (rowIndex === 0) {

                    return {
                        'background-color': '#FFF000',
                        'border-top': "none",
                        'border-bottom': "none",
                        'text-align': 'center'
                    }
                } else {
                    return {
                        'border-top': "none",
                        'border-bottom': "none",
                        'text-align': 'center',
                    }
                }
            },

            /**
             * 计划值单元格可编辑
             * @param rowNum
             */
            editLine(rowNum) {
                if (rowNum !== 0) {
                    this.rowBeingEdited = rowNum;
                    this.$refs['input' + rowNum].focus();
                    let _this = this;
                    //防止input元素未刷新出来无法，从而使获取焦点失效
                    setTimeout(function () {
                        _this.$refs['input' + rowNum].focus()
                    }, 1)
                }
            },
            editNewProjectLine(rowNum) {

                if(rowNum === 1){
                    this.newProjectDataEdited = rowNum;
                    this.$refs['newProjectInput' + rowNum].focus();
                    let _this = this;

                    //防止input元素未刷新出来无法，从而使获取焦点失效
                    setTimeout(function () {
                        _this.$refs['newProjectInput' + rowNum].focus()
                    }, 1)
                }
            },
            editFixProjectLine(rowNum) {
                if (rowNum !== 0) {
                    this.fixProjectDataEdited = rowNum;
                    this.$refs['fixProjectInput' + rowNum].focus();
                    let _this = this;

                    //防止input元素未刷新出来无法，从而使获取焦点失效
                    setTimeout(function () {
                        _this.$refs['fixProjectInput' + rowNum].focus()
                    }, 1)
                }
            },


            /**
             * 点击选中行高亮。
             * @param row
             * @param column
             * @param event
             */
            rowClick(row, column, event) {
                this.$refs.singleTable.setCurrentRow(row);
            },

            /**
             * 失去焦点修改编辑状态
             */
            loseFocus() {
                this.rowBeingEdited = -1;
                this.newProjectDataEdited = -1;
                this.fixProjectDataEdited = -1;
            },


            handleCurrentChange(val) {
                this.currentRow = val;
            },

            /**
             *
             */
            fixProject() {
                if (this.currentRow === null) {
                    this.$message({
                        message: '未选择项目',
                        type: 'warning'
                    });
                } else if (this.currentRow.projectName === '汇总') {
                    this.$message({
                        message: '未选择项目',
                        type: 'warning'
                    });
                } else {


                    this.fixProjectData[0].value = this.currentRow.projectId;
                    this.fixProjectData[1].value = this.currentRow.projectName;
                    this.fixProjectData[2].value = this.currentRow.unitId;
                    this.fixProjectData[3].value = this.currentRow.constructionNatureId;


                    this.fixProjectDialogTableVisible = true;
                }
            },

            constructionChange() {
                this.goNPage(1)
            },
            yearChange() {
                this.goNPage(1)

            },
            unitChange() {
                this.goNPage(1)
            },
            maxRowsChange() {
                this.goNPage(1);
            },

            pageNumLoseFocus() {
                this.pageNum = this.nowPageNum;
            },
            goPage() {
                this.goNPage(this.pageNum)
            },
            homePage() {
                this.goNPage(1);
            },
            endPage() {
                this.goNPage(this.totalPages);
            },
            nextPage() {
                this.goNPage(this.nowPageNum + 1);
            },
            previousPage() {
                this.goNPage(this.nowPageNum - 1);
            },
            refreshPage() {
                this.goNPage(this.nowPageNum);
            },

            goNPage(n) {
                if(n<1){
                    this.$message.error('页码不能小于1');
                    return;
                }
                if (this.totalPages === undefined){
                    this.totalPages=0;
                }
                if(n > this.totalPages){
                    n= 1;
                }



                let arr={params:{}};
                arr['params']['size'] = this.maxRowsNumOfTable;
                arr['params']['page'] = n-1;

                if(this.unitValue[0] !== -1){
                    arr['params']['unit']=this.unitValue[0];
                    if(this.unitValue[1]!==undefined){
                        arr['params']['subUnit']=this.unitValue[1];
                    }

                }

                if(this.projectCode!==""){
                    arr['params']['projectId'] = this.projectCode;
                }
                if(this.projectName!==""){
                    arr['params']['projectName'] = this.projectName;
                }
                if(this.constructionNatureValue!==1){
                    arr['params']['constructionNatureId']=this.constructionNatureValue;
                }

                let _tableHead = this.tableData[0];
                let _this = this;
                this.loadFalg = true;
                axios.get("/project/projects/",arr).then(res=>{

                    _this.nowPageNum = n;
                    _this.pageNum = n;
                    _this.tableData = res.data.data.content;
                    _this.tableData.unshift(_tableHead);
                    //_this.tableData.splice(1,_this.length-1,res.data.data.content);

                    _this.totalPages = res.data.data.totalPages;

                    _this.totalRecordingNumber = res.data.data.totalElements;
                    _this.frontRecordingNumber = (_this.nowPageNum-1)*_this.maxRowsNumOfTable;
                    _this.endRecordingNumber = _this.nowPageNum*_this.maxRowsNumOfTable>_this.totalRecordingNumber?_this.totalRecordingNumber: _this.nowPageNum*_this.maxRowsNumOfTable;



                    arr['params']['yearValue']=_this.yearValue;
                    axios.get("/planValue/planValueSummary",arr).then(res=>{


                        if(res.data.code === 200){
                            this.tableData[0].planValue = res.data.data.value.toFixed(2);
                        }else {
                            this.tableData[0].planValue = 0.00;
                        }
                    });
                    _this.getPlanValues();

                    _this.loadFalg = false;
                }).catch(err=>{
                    console.log(err);
                    this.$message.error('发生错误');
                });

            },
            search(){
                this.goNPage(1);
            },
            newProject(){
                let _this = this;
                axios.get("/project/projects/newId").then(res=>{
                    if(res.data.code === 200){
                        _this.newProjectData[0].value =  res.data.data;
                        _this.newProjectDialogTableVisible=true;
                    }
                })

            },

            addProject(){

                let regEn = new RegExp(/[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im);
                let regCn = new RegExp(/[·！＆＼‰#￥（——）：；“”‘、，|《。》？、【】[\]]/im);


                if(regEn.test(this.newProjectData[1].value) || regCn.test(this.newProjectData[1].value)){
                    this.$message({
                        message: '存在特殊字符',
                        type: 'warning',
                    });
                    return
                }

                if(this.newProjectData[1].value.length>100){
                    this.$message({
                        message: '项目名长度应小于100',
                        type: 'warning',
                    });
                    return
                }

                let data ={params: {}};
                let  _this = this;


                data["projectId"]=this.newProjectData[0].value;
                data["projectName"]=this.newProjectData[1].value;
                data["unitId"]=this.newProjectData[2].value[0];
                data["subUnitId"]=this.newProjectData[2].value[1];
                data["constructionNatureId"]=this.newProjectData[3].value;

                axios.post("/project/add",data).then(res=>{
                    if(res.data.code === 200){
                        this.$message({
                            message: '操作成功',
                            type: 'success',
                        });
                    }
                    _this.newProjectDialogTableVisible = false
                    _this.goNPage(this.pageNum);
                }).catch(err=>{
                    console.log(err);
                    this.$message.error('发生错误');
                    _this.newProjectDialogTableVisible = false

                });
            },


            fixProjectSubmit(){


                /**参数校验*/
                //let regs = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");

                let regEn = new RegExp(/[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im);
                let regCn = new RegExp(/[·！＆＼‰#￥（——）：；“”‘、，|《。》？、【】[\]]/im);


                if(regEn.test(this.fixProjectData[1].value) || regCn.test(this.fixProjectData[1].value)){
                    this.$message({
                        message: '存在特殊字符',
                        type: 'warning',
                    });
                    return
                }

                if(this.fixProjectData[1].value.length>100){
                    this.$message({
                        message: '项目名长度应小于100',
                        type: 'warning',
                    });
                    return
                }
                let data ={params: {}};
                let  _this = this;

                data["projectId"]=this.fixProjectData[0].value;
                data["projectName"]=this.fixProjectData[1].value;
                data["unitId"]=this.fixProjectData[2].value[0];
                data["subUnitId"]=this.fixProjectData[2].value[1];
                data["constructionNatureId"]=this.fixProjectData[3].value;


                axios.put("/project/update",data).then(res=>{
                    if(res.data.code === 200){
                        _this.$message({
                            message: '操作成功',
                            type: 'success',
                        });
                    }
                    _this.fixProjectDialogTableVisible = false
                    _this.goNPage(this.pageNum);

                }).catch(err=>{
                    console.log(err);
                    this.$message.error('发生错误');
                    _this.fixProjectDialogTableVisible = false
                });
            },
            init() {
                let _this = this;

                axios.get("/ConstructionNature/AllConstructionNature")
                    .then(res=> {
                        _this.constructionNature = res.data.data;
                    }).catch(err=> {
                    _this.constructionNature=null;
                    console.log(err);
                });

                axios.get("/unit/allUnit").then(res=>{
                    _this.unit = res.data.data;
                }).catch(err=>{
                    _this.unit=null;
                    console.log(err);
                });
                this.goNPage(1);
            },

            getPlanValues(){
                let _this = this;
                for (let i=1;i<_this.tableData.length;i++){
                    let arr={params:{}};
                    arr['params']['projectId'] = _this.tableData[i].projectId;
                    arr['params']['yearValue'] = _this.yearValue;

                    axios.get("/planValue/planValue",arr).then(res=>{
                        if(res.data.code == 200){
                            _this.tableData[i].planValue = res.data.data.planValue.toFixed(2);
                            _this.tableData[i].oldPlanValue = res.data.data.planValue;
                        }
                    }).catch(err=>{
                        console.log(err);
                    });


                }
            },
            saveData(){

               if(this.modifyFlag){
                   let _this = this;

                   if(this.yearValue=== undefined||this.yearValue=== null){
                       this.$message({
                           message: '未选择年份',
                           type: 'warning'
                       });
                       return
                   }

                   for (let i = 1;i<this.tableData.length;i++){
                       if(_this.tableData[i].modifyFlag){
                           let arr = {}
                           arr['yearValue']=_this.yearValue;
                           arr['projectId']=_this.tableData[i].projectId;
                           arr['planValue']=_this.tableData[i].planValue;

                           axios.post("/planValue/planValue",arr)
                               .then(res=>{
                                   if(res.data.code === 200){
                                       _this.$message({
                                           message: '操作成功',
                                           type: 'success',
                                       });
                                       _this.goNPage(_this.pageNum);
                                   }
                               }).catch(err=>{
                               console.log(err);
                           })
                       }

                   }
               }
            },
            newProjectloseFocus(){
                this.newProjectDataEdited=-1;
                if(this.newProjectData[1].value===""){
                    this.newProjectData[1].value="新建项目名";
                    this.$message({
                        message: '项目名不能为空',
                        type: 'warning'
                    });
                }
            },
            fixProjectloseFocus(){
                this.fixProjectDataEdited=-1;
                if(this.fixProjectData[1].value===""){
                    this.fixProjectData[1].value="新建项目名";
                    this.$message({
                        message: '项目名不能为空',
                        type: 'warning'
                    });
                }
            },


            tableIntPutLoseFocus: function (rowNum) {
                this.rowBeingEdited = -1;
                let rowData = this.tableData[rowNum];
                let f = parseFloat(rowData.planValue.toString());
                if (isNaN(f)||f>100||f<0){
                    this.$message.error('计划值是区间(0,100)的数字哦！');
                    rowData.planValue = rowData.oldPlanValue;
                }
                else {
                    let sum = parseFloat( this.tableData[0].planValue.toString());


                    this.tableData[0].planValue= (sum - parseFloat(rowData.oldPlanValue.toString()) + f).toFixed(2);
                    rowData.planValue = f.toFixed(2);

                    rowData.oldPlanValue = rowData.planValue;
                    rowData.modifyFlag = true;
                    this.modifyFlag = true;
                }

            },

        },



        mounted:function(){
            this.init();//需要触发的函数
        }

    };

</script>

<style scoped>
    .fp_header {
        text-align: center;
        font-size: small;

    }

    .fp_main {
        text-align: center;
        margin-bottom: 10px;


    }

    .fp_footer {
        width: 100%;
        height: 35px;
        background-color: #F4F4F4;
        font-size: small;
    }

    .table {
        border-left: 5px #FFF000 solid;
        padding: 4px 0;
    }

    .first_col {
        background-color: #EEEEEE;
    }

    .firstRowCell {
        background-color: #FFF000;
    }

    /* 清除底部横线 */
    .el-table::before {
        height: 0px;
    }

</style>