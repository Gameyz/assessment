<template>
    <div id="indexPage">
        <el-button type="primary" @click="addRoutes1">功能页面</el-button>
        <el-button type="success" @click="addRoutes2">静态页面</el-button>
        <el-button type="info" @click="addRoutes3">日志页面</el-button>
    </div>
</template>

<script>
    export default {
        name: "Index",
        methods: {
            addRoutes1() {
                this.$router.push('/FunctionPage')
            },
            addRoutes2() {
                this.$router.push('/StaticPage')
            },
            addRoutes3() {
                this.$router.push('/LogPage')
            }
        }
    }

</script>

<style scoped>
    #indexPage {
        text-align: center;
        margin: auto auto
    }
</style>