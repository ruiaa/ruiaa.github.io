#   npm install axios

#   响应结构

    {
      data: {},             // 由服务器提供的响应
      status: 200,          // 来自服务器响应的 HTTP 状态码
      statusText: 'OK',     // 来自服务器响应的 HTTP 状态信息
      headers: {},          // 服务器响应头
      config: {}            // 为请求提供的配置信息
    }
    
    --->  then(function(response) { ... })
    
#   get

    // 为给定 ID 的 user 创建请求，/user?id=12345
    axios.get('/user', {
        params: {
          id: 12345
        }
      })
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });   
      
    // or
    axios.get('/user?id=12345')
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });

      
#   POST 请求

    axios.post('/user', {
        firstName: 'Fred',
        lastName: 'Flintstone'
      })
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });

#   多个并发请求，都完成后才继续进行 then()。

    function getUserAccount() {
      return axios.get('/user/12345');
    }
    
    function getUserPermissions() {
      return axios.get('/user/12345/permissions');
    }
    
    axios.all([getUserAccount(), getUserPermissions()])
      .then(axios.spread(function (acct, perms) {
        // 两个请求现在都执行完成
      }));




#	配置















