<template>
    <v-card>
        <div>
            
        <v-spacer></v-spacer>
        <img src="@/assets/images/main/logo(Bg).png" alt="로고" class="loginImg">
        <v-spacer></v-spacer>

        <h2 class="titleText grey lighten-2">My Page</h2>
        </div>

        <form class="col-9 formBox">
            <v-text-field label="ID" type="text" class="col-8"></v-text-field>
            <v-text-field label="NAME" type="text" class="col-8"></v-text-field>
            <v-text-field label="AGE" type="text" class="col-8"></v-text-field>
            <v-text-field label="MAJOR" type="text" class="col-8"></v-text-field>
        </form>
    </v-card>
</template>


<script>
export default {
    name: "MyPage"
}
</script>

<style>

</style>