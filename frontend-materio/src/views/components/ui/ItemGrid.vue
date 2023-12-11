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

        <process-participant-chat></process-participant-chat>
        
    </v-container>
</template>

<script>
import ProcessParticipantChat from '../../../components/ProcessParticipantChat.vue'
import BaseGrid from '../base-ui/BaseGrid.vue'

import Item from '../Item.vue'


export default {
    name: 'itemGrid',
    mixins:[BaseGrid],
    components:{
        Item,
        ProcessParticipantChat,
    },
    data: () => ({
        path: 'items',
    }),
    watch: {
    },
    methods:{
    }
}

</script>
