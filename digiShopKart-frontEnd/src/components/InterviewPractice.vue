<template>
<div>
    <h1>InterviewPractice Practice</h1>
    <button>
        Eww
    </button>
</div>
</template>

<script>
import { computed, ref, reactive, watch } from 'vue';

export default{
    setup(){
        const count = ref(0);
        const state = reactive({
            name : 'Ajay'
        })

        const doubleCount= computed(()=> count.value *  2);

        watch(count, (newVal)=>{
            console.log(`Count changed to : ${newVal}`);
            
        })
        return {count , state, doubleCount}
    }
}
</script>