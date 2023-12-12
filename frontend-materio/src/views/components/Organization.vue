<template>
    <div>
        <String label="부서명" v-model="value.businessRegistrationNumber" :editMode="editMode"/>
        <String label="직급" v-model="value.accountName" :editMode="editMode"/>
        <String label="이름" v-model="value.representative" :editMode="editMode"/>

        <v-divider class="border-opacity-100 my-divider"></v-divider>
        <v-layout row justify-end>
            <v-btn
                width="64px"
                color="primary"
                @click="save"
            >
                저장
            </v-btn>
        </v-layout>
    </div>
</template>


<script>

import BaseEntity from './base-ui/BaseEntity.vue'
import String from './primitives/String.vue'
import Address from './vo/Address.vue'

export default {
    name: 'Account',
    mixins:[BaseEntity],
    components:{
        String,
        Address,
    },
    
    data: () => ({
        path: "organizations"
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>
