<template>
    <div>
        <div class="Log_page">
            <div class="log_fp">
                <el-form :inline="true" class="demo-form-inline" @submit.native.prevent>
                    <el-form-item label=时间>
                        <el-date-picker
                                v-model="data"
                                type="datetimerange"
                                range-separator="至"
                                start-placeholder="开始日期"
                                end-placeholder="结束日期">
                        </el-date-picker>
                    </el-form-item>
                </el-form>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "LogPage",
        data() {
            return{
                data:null,
            }
        },
    }
</script>

<style scoped>

</style>