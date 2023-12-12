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

        <OrganizationChartChat />
       
    </v-container>
</template>

<script>
import BaseGrid from '../base-ui/BaseGrid.vue'

import Organization from '../Organization.vue'
import Address from '../vo/Address.vue'
import OrganizationChartChat from '../../../components/OrganizationChartChat.vue'

export default {
    name: 'organizationGrid',
    mixins:[BaseGrid],
    components:{
        Organization,
        Address,
        OrganizationChartChat
    },
    data: () => ({
        path: 'organizations',
    }),
    watch: {
    },
    methods:{
    }
}

</script>
