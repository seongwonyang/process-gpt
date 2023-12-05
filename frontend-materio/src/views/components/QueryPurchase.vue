<template>
    <v-card outlined style="border-radius: 0; margin-top: 10px; padding-bottom: 10px; background-color: #F4F5FA;">
        <v-card-title>
            QueryPurchase
        </v-card-title>
    
        <v-card-text style="margin-top:-30px;">
            <v-row no-gutters style="margin: 15px 0px -10px -15px;">
                <v-col style="max-width:140px;">
                    <String class="attributes-list" label="AccountId" v-model="value.parameters.accountId" :editMode="editMode"/>
                </v-col>
                <v-col style="max-width:140px;">
                    <String class="attributes-list" label="MainShipName" v-model="value.parameters.mainShipName" :editMode="editMode"/>
                </v-col>
                <v-col style="max-width:140px;">
                    <Date class="attributes-list" label="PurchaseDateFrom" v-model="value.parameters.purchaseDateFrom" :editMode="editMode"/>
                </v-col>
                <v-col style="max-width:140px;">
                    <Date class="attributes-list" label="PurchaseDateTo" v-model="value.parameters.purchaseDateTo" :editMode="editMode"/>
                </v-col>
                <v-col>
                    <v-btn class="gs-query-search-btn contrast-primary-text"
                        @click="search"
                        small
                        style="margin-left: 35px; margin-top: 30px;"
                        color="primary"
                    >
                        <v-icon small>mdi-magnify</v-icon>검색
                    </v-btn>
                </v-col>
            </v-row>
        </v-card-text>
    </v-card>
</template>

<script>
import String from './primitives/String.vue'
import Date from './primitives/Date.vue'

export default {
    name: 'QueryPurchase',
    components:{
        String,
        Date,
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {
            apiPath: 'purchases/search/findByQueryPurchase',
            parameters: {}
        },
    }),
    created() {
    },
    watch: {
    },
    methods: {
        search() {
            let search = null;
            search = this.value;
            this.$emit('search', search);
        },
        change() {
            this.$emit("update:modelValue", this.value);
        },
    }
}
</script>

<style>
.attributes-list{
    margin-left: 15px;
}
</style>
