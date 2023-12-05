<template>
    <v-container>
        <v-snackbar
            v-model="snackbar.status"
            :timeout="snackbar.timeout"
            :color="snackbar.color"
        >
            
            <v-btn style="margin-left: 80px;" text @click="snackbar.status = false">
                Close
            </v-btn>
        </v-snackbar>
        <div class="panel">
            <div class="gs-bundle-of-buttons" style="max-height:10vh;">
                <v-btn @click="addNewRow" @class="contrast-primary-text" small color="primary">
                    <v-icon small style="margin-left: -5px;">mdi-plus</v-icon>등록
                </v-btn>
                <v-btn style="margin-left: 5px;" @click="editSelectedRow()" class="contrast-primary-text" small color="primary">
                    <v-icon small>mdi-pencil</v-icon>수정
                </v-btn>
                <v-btn style="margin-left: 5px;" @click="saleDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>sale
                </v-btn> 
                <v-dialog v-model="saleDialog" width="500">
                    <SaleCommand
                        @closeDialog="saleDialog = false"
                        @sale="sale"
                    ></SaleCommand>
                </v-dialog>
            </div>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>PurchaseType</th>
                        <th>PurchaseDate</th>
                        <th>WarehouseArrivalDate</th>
                        <th>StorageFeePaymentDate</th>
                        <th>StorageFeePaymentStatus</th>
                        <th>MainShipName</th>
                        <th>ProductName</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(val, idx) in value" :key="val" @click="changeSelectedRow(val)" :style="val === selectedRow ? 'background-color: #f0f3ff;':''">
                            <td class="font-semibold">{{ idx + 1 }}</td>
                            <td class="whitespace-nowrap" label="매입구분">{{ val.purchaseType }}</td>
                            <td class="whitespace-nowrap" label="매입일자">{{ val.purchaseDate }}</td>
                            <td class="whitespace-nowrap" label="입고일자">{{ val.warehouseArrivalDate }}</td>
                            <td class="whitespace-nowrap" label="보관료부담일자">{{ val.storageFeePaymentDate }}</td>
                            <td class="whitespace-nowrap" label="보관료부담여부">{{ val.storageFeePaymentStatus }}</td>
                            <td class="whitespace-nowrap" label="본선명">{{ val.mainShipName }}</td>
                            <td class="whitespace-nowrap" label="품명">{{ val.productName }}</td>
                            <Icon style="margin-top: 15px;" icon="mi:delete" @click="deleteRow(val)" />
                        </tr>
                    </tbody>
                </v-table>
            </div>
            <PurchaseDetailDetailGrid style="margin-top: 20px;" label="PurchaseDetails" offline v-if="selectedRow" v-model="selectedRow.purchaseDetails" :selectedRow="selectedRow"/>
        </div>
        <v-col>
            <v-dialog
                v-model="openDialog"
                transition="dialog-bottom-transition"
                width="35%"
            >
                <v-card>
                    <v-toolbar
                        color="primary"
                        class="elevation-0"
                        height="50px"
                    >
                        <div style="color:white; font-size:17px; font-weight:700;">Company 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <Purchase :offline="offline"
                            :isNew="!value.idx"
                            :editMode="true"
                            :inList="false"
                            v-model="newValue"
                            @add="append"
                        />
                    </v-card-text>
                </v-card>
            </v-dialog>
        </v-col>
        <v-col style="margin-bottom:40px;">
            <div class="text-center">
                <v-dialog
                    width="332.5"
                    fullscreen
                    hide-overlay
                    transition="dialog-bottom-transition"
                >
                    <v-btn
                        style="position:absolute; top:2%; right:2%"
                        @click="closeDialog()"
                        depressed
                        icon 
                        absolute
                    >
                        <v-icon>mdi-close</v-icon>
                    </v-btn>
                </v-dialog>
            </div>
        </v-col>
    </v-container>
</template>

<script>
import BaseGrid from '../base-ui/BaseGrid.vue'

import Purchase from '../Purchase.vue'
import PurchaseDetailDetailGrid from './PurchaseDetailDetailGrid.vue'


export default {
    name: 'purchaseGrid',
    mixins:[BaseGrid],
    components:{
        Purchase,
        PurchaseDetailDetailGrid,
    },
    data: () => ({
        path: 'purchases',
        saleDialog: false,
    }),
    watch: {
    },
    methods:{
        sale(params){
            try{
                this.repository.invoke(this.getSelectedItem(), "sale", params)
                this.$EventBus.$emit('show-success','sale 성공적으로 처리되었습니다.')
                this.saleDialog = false
            }catch(e){
                console.log(e)
            }
            
        },
    }
}

</script>
