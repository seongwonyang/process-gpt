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

        <ProcessManagerChat />
       
    </v-container>
</template>

<script>
import BaseGrid from '../base-ui/BaseGrid.vue'

import Account from '../Account.vue'
import Address from '../vo/Address.vue'
import ProcessManagerChat from '../../../components/ProcessManagerChat.vue'

export default {
    name: 'accountGrid',
    mixins:[BaseGrid],
    components:{
        Account,
        Address,
        ProcessManagerChat
    },
    data: () => ({
        path: 'accounts',
    }),
    watch: {
    },
    methods:{
    }
}

</script>
