<template>
    <div>
        <String label="ActivityId" v-model="value.activityId" :editMode="editMode"/>
        <String label="ActivityName" v-model="value.activityName" :editMode="editMode"/>
        <Date label="시작일" v-model="value.startDate" :editMode="editMode"/>
        <Date label="완료일" v-model="value.endDate" :editMode="editMode"/>
        <Date label="만료일" v-model="value.dueDate" :editMode="editMode"/>
        <String label="프로세스 정의 ID" v-model="value.processDefinitionId" :editMode="editMode"/>
        <String label="프로세스 인스턴스 ID" v-model="value.processInstanceId" :editMode="editMode"/>
        <String label="UserId" v-model="value.userId" :editMode="editMode"/>

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
import Date from './primitives/Date.vue'

export default {
    name: 'Task',
    mixins:[BaseEntity],
    components:{
        String,
        Date,
    },
    
    data: () => ({
        path: "tasks"
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>
