const SID = {
    SYSTEM_ERROR: 0,
    BUSINESS_EXCEPTION: 1,
    USER_CONNECT: 100,
    HOMEPAGE: 101,
    USER_ANSWER: 102,
    NEW_WORD: 103,
    USER_DISCONNECT: 104,
}
const CID = {
    HOMEPAGE: 100,
    ANSWER: 101,
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
            submit_answer: function (event) {
                ws.send(JSON.stringify({'cid': CID.ANSWER, 'body': this.input_word}))
            },
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
            }
        }
    }
)
var data = vm.$data;
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
            if (body.userId !== data.userId) {
                data.users.push({userId: body.userId, word: ""})
                data.users.sort()
            }
            break;
        case SID.HOMEPAGE:
            data.userId = body.userId;
            data.word = body.curIdiom;
            body.userIds.sort();
            body.userIds.forEach((uid, idx) => {
                if (uid !== data.userId) {
                    data.users.push({userId: uid, word: ""})
                }
            });
            break;
        case SID.USER_ANSWER:
            user = data.users.find((user) => user.userId === body.userId)
            if (user !== undefined) {
                console.log(user)
                user.word = body.word;
            }
            if (body.userId === data.userId) {
                vm.notifyResult(body.correct)
            }

            break;
        case SID.NEW_WORD:
            data.word = body;
            break;
        case SID.USER_DISCONNECT:
            var index = data.users.findIndex((val, idx) => {
                return val.userId === body;
            })
            if (index !== undefined && index >= 0) {
                data.users.splice(index, 1)
            }
            break;

    }


};
ws.onclose = function () {
    // 关闭 websocket
    vm.$message("连接已关闭...");
}
