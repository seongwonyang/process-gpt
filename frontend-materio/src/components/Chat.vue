<template>
    <div>
        <v-btn @click="handleClick"
                class="chat-open-btn"
                color="primary"
                icon 
                dark
                large
        >
            <v-icon v-if="openChatBox">mdi-close</v-icon>
            <img v-else src="chatgpt.svg" class="chatgpt-icon" />
        </v-btn>

        <v-card v-if="openChatBox" class="chat-open-box">
            <v-card-text class="message-box" ref="messages">
                <div v-for="(message, index) in messages"
                        :key="index"
                >
                    <div v-if="message.role == 'user'"
                            class="d-flex justify-end my-2"
                    >
                        <div class="user-message">

                            

                            {{ message.text }} 
                        </div>
                        <div class="ml-1">
                            <v-avatar size="48">
                                <v-icon>
                                    mdi-account-circle
                                </v-icon>
                            </v-avatar>
                            <div class="subtitle-2 text-center">
                                {{ message.role }}
                            </div>
                        </div>
                    </div>

                    <div v-else-if="message.role == 'system'"
                            class="d-flex justify-start my-2"
                    >
                        <div class="mr-2">
                            <v-avatar size="48">
                                <v-icon>
                                    mdi-account-circle
                                </v-icon>
                            </v-avatar>
                            <div class="subtitle-2 text-center">
                                {{ message.role }}
                            </div>
                        </div>
                        <div class="d-flex system-message">
                            <div v-html="message.text"></div>
                        </div>

                        <vue-bpmn v-if="message.bpmn"
                                :xml="message.bpmn"
                                :options="options"
                                v-on:error="handleError"
                                v-on:shown="handleShown"
                                v-on:loading="handleLoading"
                            ></vue-bpmn>
                    </div>
                </div>

                <div v-if="loading" class="d-flex justify-start my-2">
                    <div class="mr-2">
                        <v-avatar size="48">
                            <v-icon>
                                mdi-account-circle
                            </v-icon>
                        </v-avatar>
                        <div class="subtitle-2 text-center">
                            system
                        </div>
                    </div>
                    <div class="d-flex system-message">
                        <v-progress-circular
                                indeterminate
                                color="grey"
                        ></v-progress-circular>
                    </div>
                </div>
            </v-card-text>

            <v-card-actions class="chat-box">
                
                            
                <v-text-field
                        v-model="newMessage"
                        @keydown.enter="sendMessage"
                        label="Send Message"
                        autofocus
                        dense
                        class="px-2"
                >
                    <template v-slot:append>
                        <v-btn @click="sendMessage"
                                color="primary"
                                icon
                                small
                        >
                            <v-icon>mdi-send</v-icon>
                        </v-btn>
                    </template>
                </v-text-field>
            </v-card-actions>
        </v-card>
    </div>
</template>

<script>
import ChatGenerator from "../ai/ChatGenerator.js";
import apiSpec from "../api/openapi.js";

import BaseRepository from "../repository/BaseRepository";
import axios from "@axios";
import JSONFormatter from 'json-formatter-js'; 
import VueBpmn from './Bpmn.vue';

export default {
    name: 'Chat',
    components: {
        VueBpmn
    },
    data: () => ({
        messages: [],
        newMessage: "",
        generator: null,
        loading: false,
        openChatBox: false,
        bpmn: ``
    }),
    created() {
        this.generator = new ChatGenerator(this, {
            isStream: true,
            preferredLanguage: "Korean"
        });
        this.init();
    },
    watch: {
        messages() {
            this.$nextTick(() => {
                let messages = this.$refs.messages;
                messages.scrollTo({ top: messages.scrollHeight, behavior: 'smooth' });
            });
        },
    },
    methods:{
        handleClick() {
            this.openChatBox = !this.openChatBox;
        },
        init() {
            
        },

        sendMessage() {
            if (this.newMessage !== "") {
                this.loading = true;
                this.init();
                
                this.messages.push(
                    {
                        role: "user",
                        text: this.newMessage
                    }
                );

                this.generator.generate();
    

                this.messages.push({
                    role:'system',
                    text: '.'
                });

                this.newMessage = "";
            }
        },

        onModelCreated(response){

            let messageWriting = this.messages[this.messages.length -1]
            messageWriting.text = response

            debugger;
            let bpmn = this.extractXML(response)
            if(!bpmn)
                bpmn = this.extractBPMN(response)
            if(!bpmn)
                bpmn = this.extractCode(response)

console.log(bpmn)

            if(bpmn){
                messageWriting.bpmn = bpmn
            }

        },

        extractXML(text) {            
            const regex = /```xml\s*([\s\S]*?)(?:\n\s*```|$)/;
            const match = text.match(regex);
            return match ? match[1].trim() : null;
        },
        extractBPMN(text) {
            const regex = /```bpmn\s*([\s\S]*?)(?:\n\s*```|$)/;
            const match = text.match(regex);
            return match ? match[1].trim() : null;
        },
        extractCode(text) {
            const regex = /```\s*([\s\S]*?)(?:\n\s*```|$)/;
            const match = text.match(regex);
            return match ? match[1].trim() : null;
        },

        onGenerationFinished(responses){
            // console.log(responses);
            this.loading = false;
            var message;

            // responses.forEach(response=> {
            //     console.log(response);

            //     if (response.error) {
            //         message = {
            //             role:'system',
            //             text: response.error.speak ? response.error.speak : response.error
            //         };

            //         this.messages.push(message);

            //     }
            //     else if (response.command) {
            //         if (response.thoughts) {
            //             // if (response.thoughts.plan && response.thoughts.plan.length > 0) {
            //             //     response.thoughts.plan.forEach((planMsg, index) => {
            //             //         message = {
            //             //             role:'system',
            //             //             text: planMsg
            //             //         };

            //             //         if (index == response.thoughts.plan.length-1) {
            //             //             message.command = response.command
            //             //         }

            //             //         this.messages.push(message);
            //             //     })
            //             // }

            //             if (response.thoughts.criticism) {
            //                 message = {
            //                     role:'system',
            //                     text: response.thoughts.criticism,
            //                 };

            //                 this.messages.push(message);
            //             }

            //             if (response.thoughts.speak) {
            //                 message = {
            //                     role:'system',
            //                     text: response.thoughts.speak,
            //                     command: response.command
            //                 };

            //                 this.messages.push(message);

            //             }
            //         }

            //         if (!message && response.command) {
            //             message = {
            //                 role:'system',
            //                 text: response.command.name + "을 다음의 아규먼트로 실행합니다: "
            //                     + JSON.stringify(response.command.args),
            //                 command: response.command
            //             };

            //             this.messages.push(message);
            //         }
            //     }
            //     else if (response.markdown) {
            //         message = {
            //             role:'system',
            //             text: response.markdown.replace(/(?:\r\n|\r|\n)/g, '<br />')
            //         };

            //         this.messages.push(message);

            //     }
            // });
        },

        onError(error) {
            this.loading = false;

            if (error.code === "invalid_api_key") {
                var apiKey = prompt("API Key 를 입력하세요.");
                localStorage.setItem("openAIToken", apiKey);
                
                this.generator.generate();
                
            } else {
                console.log(error)
                var message = {
                    role:'system',
                    text: error.message
                };

                this.messages.push(message);
            }
        },

        async doit(message) {
            this.loading = true;

            if (message.command.method === 'GET') {
                var path = message.command.path;
                this.$router.push(`${path}`);
                this.loading = false;

            } else {
                await this.submit(message).then((res) => {
                    if (res.data) {
                        this.formatMarkdown(JSON.stringify(res.data));
                    } else {
                        this.formatMarkdown(JSON.stringify(res));
                    }
                }).catch(error => {
                    if (error.response) {
                        this.onError(error);
                    } else {
                        this.loading = false;
                        var message = {
                            role: 'system',
                            text: error
                        }
                        this.messages.push(message);
                    }
                })
            }
        },

        async submit(message) {
            var path = message.command.path;
            if (path.charAt(0) === '/') {
                path = path.substr(1);
            }
            var value = message.command.args;
            var repository = new BaseRepository(axios, path);

            if (message.command.method === 'POST') {
                return await repository.save(value, true);
            } else if (message.command.method === 'PUT') {
                return await repository.save(value, false);
            } else if (message.command.method === 'DELETE') {
                return await repository.delete(value);
            }
        },

        async formatMarkdown(value) {
            this.generator.previousMessages = [{
                role: 'system',
                content: `
You should only respond in JSON format as described below

RESPONSE FORMAT:
[{
    "markdown": "generated markdown text"
}]
                `
            }];
            this.newMessage = `
Generate the following values in markdown text format:
${value}
`;

            await this.generator.generate();

            this.newMessage = "";
        },

    }
}
</script>

<style scoped>
.chat-open-btn {
    position: fixed;
    z-index: 999;
    bottom: 15px;
    right: 15px;
}

.chatgpt-icon {
    width: 30px;
    height: 30px;
}
.chat-open-box {
    position: fixed;
    z-index: 999;
    bottom: 90px;
    right: 15px;
    width: 1000px;
    max-width: 1200px;
    height: 800px;
    max-height: 600px;
    padding: 5px;
}
.user-message {
    background: #9155FD;
    color: #ffffff;
    font-weight: bold;
    padding: 12px;
    border-radius: 20px;
}

.system-message {
    background: #eeeeee;
    font-weight: bold;
    padding: 12px;
    border-radius: 20px;
    max-width: 280px;
}

.system-message > div {
    max-width: 180px;
}

.message-box {
    overflow-y: auto;
    max-height: 435px;
}

.chat-box {
    position: absolute;
    bottom: 0px;
    right: 0px;
    width: 100%;
}

</style>