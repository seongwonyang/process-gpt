<template>
    <div>
        <Number label="아이디" v-model="value.purchaseNo" :editMode="editMode"/>
        <String label="매입구분" v-model="value.purchaseType" :editMode="editMode"/>
        <Date label="매입일자" v-model="value.purchaseDate" :editMode="editMode"/>
        <Date label="입고일자" v-model="value.warehouseArrivalDate" :editMode="editMode"/>
        <Date label="보관료부담일자" v-model="value.storageFeePaymentDate" :editMode="editMode"/>
        <Boolean label="보관료부담여부" v-model="value.storageFeePaymentStatus" :editMode="editMode"/>
        <String label="본선명" v-model="value.mainShipName" :editMode="editMode"/>
        <String label="품명" v-model="value.productName" :editMode="editMode"/>
        <!-- <AccountId offline label="프로세스 관리" v-model="value.accountId" :editMode="editMode" @change="change"/>
        <PurchaseDetailDetailGrid label="PurchaseDetails" offline v-model="value.purchaseDetails" :editMode="editMode" @change="change"/> -->

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
import Number from './primitives/Number.vue'
import String from './primitives/String.vue'
import Date from './primitives/Date.vue'
import Boolean from './primitives/Boolean.vue'
// import AccountId from './AccountId.vue'
// import PurchaseDetailDetailGrid from './ui/PurchaseDetailDetailGrid.vue'

export default {
    name: 'Purchase',
    mixins:[BaseEntity],
    components:{
        Number,
        String,
        Date,
        Boolean,
        // AccountId,
        // PurchaseDetailDetailGrid,
    },
    
    data: () => ({
        path: "purchases"
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>
