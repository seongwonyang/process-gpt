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
                <v-btn style="margin-left: 5px;" @click="updateTaskDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('User')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>작업 수정
                </v-btn> 
                <v-dialog v-model="updateTaskDialog" width="500">
                    <UpdateTaskCommand
                        @closeDialog="updateTaskDialog = false"
                        @updateTask="updateTask"
                    ></UpdateTaskCommand>
                </v-dialog>
            </div>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>ActivityId</th>
                        <th>ActivityName</th>
                        <th>StartDate</th>
                        <th>EndDate</th>
                        <th>DueDate</th>
                        <th>ProcessDefinitionId</th>
                        <th>ProcessInstanceId</th>
                        <th>UserId</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(val, idx) in value" :key="val" @click="changeSelectedRow(val)" :style="val === selectedRow ? 'background-color: #f0f3ff;':''">
                            <td class="font-semibold">{{ idx + 1 }}</td>
                            <td class="whitespace-nowrap" label="ActivityId">{{ val.activityId }}</td>
                            <td class="whitespace-nowrap" label="ActivityName">{{ val.activityName }}</td>
                            <td class="whitespace-nowrap" label="시작일">{{ val.startDate }}</td>
                            <td class="whitespace-nowrap" label="완료일">{{ val.endDate }}</td>
                            <td class="whitespace-nowrap" label="만료일">{{ val.dueDate }}</td>
                            <td class="whitespace-nowrap" label="프로세스 정의 ID">{{ val.processDefinitionId }}</td>
                            <td class="whitespace-nowrap" label="프로세스 인스턴스 ID">{{ val.processInstanceId }}</td>
                            <td class="whitespace-nowrap" label="UserId">{{ val.userId }}</td>
                            <Icon style="margin-top: 15px;" icon="mi:delete" @click="deleteRow(val)" />
                        </tr>
                    </tbody>
                </v-table>
            </div>
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
                        <Task :offline="offline"
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

import Task from '../Task.vue'


export default {
    name: 'taskGrid',
    mixins:[BaseGrid],
    components:{
        Task,
    },
    data: () => ({
        path: 'tasks',
        updateTaskDialog: false,
    }),
    watch: {
    },
    methods:{
        updateTask(params){
            try{
                this.repository.invoke(this.getSelectedItem(), "updateTask", params)
                this.$EventBus.$emit('show-success','UpdateTask 성공적으로 처리되었습니다.')
                this.updateTaskDialog = false
            }catch(e){
                console.log(e)
            }
            
        },
    }
}

</script>
