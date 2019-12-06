const SID = {
    SYSTEM_ERROR: 0,
    BUSINESS_EXCEPTION: 1,
    USER_CONNECT: 100,
    HOMEPAGE: 101,
    USER_ANSWER: 102,
    NEW_WORD: 103,
    USER_DISCONNECT: 104,
    USER_INPUT_CHANGE: 105,
}
const CID = {
    HOMEPAGE: 100,
    ANSWER: 101,
    INPUT_CHANGE: 102,
}
Object.freeze(SID)
Object.freeze(CID)
var vm = new Vue({
        el: '#clu',
        data: {
            word: "",
            input_word: "",
            userId: 0,
            users: []

        },
        methods: {

            notifyResult: function (correct) {
                if (correct) {
                    this.$message({
                        message: '回答正确',
                        type: 'success'
                    });
                } else {
                    this.$message({
                        message: '回答错误',
                        type: 'warning'
                    })
                }
            },
            reset_input: function () {
                this.input_word = ''
            }
        },
        watch: {
            input_word: function (new_word, old_word) {
                ws.send(JSON.stringify({cid: CID.INPUT_CHANGE, body: new_word}))
                if (new_word.length == 4) {
                    ws.send(JSON.stringify({'cid': CID.ANSWER, 'body': new_word}))
                }
            }
        }
    }
)
// 打开一个 web socket
var ws = new WebSocket("ws://localhost:8080");
ws.onopen = function () {
    // Web Socket 已连接上，使用 send() 方法发送数据
    ws.send(JSON.stringify({'cid': CID.HOMEPAGE, 'body': ''}))
};
ws.onmessage = function (evt) {
    var received_msg = evt.data;
    console.log(received_msg);
    result = JSON.parse(received_msg);
    sid = result.sid;
    if (sid === SID.SYSTEM_ERROR || sid === SID.BUSINESS_EXCEPTION) {
        vm.$message(received_msg);
        return;
    }
    var body = result.body;
    switch (sid) {
        case SID.USER_CONNECT:
            if (body.userId !== vm.userId) {
                vm.users.push({userId: body.userId, word: ""})
                vm.users.sort()
            }
            break;
        case SID.HOMEPAGE:
            vm.userId = body.userId;
            vm.word = body.curIdiom;
            body.userIds.sort();
            body.userIds.forEach((uid, idx) => {
                if (uid !== vm.userId) {
                    vm.users.push({userId: uid, word: ""})
                }
            });
            break;
        case SID.USER_ANSWER:
            user = vm.users.find((user) => user.userId === body.userId)
            if (user !== undefined) {
                user.word = body.word;

            }
            if (body.userId === vm.userId) {
                vm.notifyResult(body.correct)
                if (body.correct) {
                    vm.reset_input()
                }

            }

            break;
        case SID.NEW_WORD:
            vm.word = body;
            break;
        case SID.USER_DISCONNECT:
            var index = vm.users.findIndex((val, idx) => {
                return val.userId === body;
            })
            if (index !== undefined && index >= 0) {
                vm.users.splice(index, 1)
            }
            break;
        case SID.USER_INPUT_CHANGE:
            user = vm.users.find((user) => user.userId === body.userId)
            if (user !== undefined) {
                user.word = body.word;
            }


    }


};
ws.onclose = function () {
    // 关闭 websocket
    vm.$message("连接已关闭...");
}
