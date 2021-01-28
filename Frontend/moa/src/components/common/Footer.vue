<template>
    <footer class="height-150">
        <v-container class="col-12 height-150">
            <v-row class="height-150">
                <div class="col-2">
                    <img src='@/assets/images/main/logo.png' alt="푸터로고">
                </div>
                <div class="col-8">
                    <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. </p>
                    <p>Contrary to popular belief, Lorem Ipsum is not simply random text.</p>
                    <p>The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. </p>
                    <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. </p>
                </div>
                <div class="col-2">
                    <p>Together</p>
                </div>
                
            </v-row>
        </v-container>
    </footer>
</template>
<style>

footer{
    background-color:lightslategrey;
}
footer .row div{
    height:100%;
}
    footer .row div:first-child img{
        margin:0 auto;
    }
    footer .row div:nth-child(2) p{
        margin:0; 
        font-size:16px;
        line-height:32px;
        text-align:center;
    }
    footer .row div:nth-child(3) p{
        width:100%;
        height:100%;
        margin:0;
        text-align:center;
        font-size:60px;
        
    }

    .height-150{
        height:150px;
    
    }
    
</style>