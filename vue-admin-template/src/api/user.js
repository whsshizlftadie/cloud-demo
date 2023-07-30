import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/auth/oauth/token',
    method: 'post',
    params: data
  })
}

export function getInfo() {
  return request({
    url: '/api/user/current',
    method: 'get'
  })
}

export function logout() {
  return request({
    url: '/vue-admin-template/user/logout',
    method: 'post'
  })
}

export function listPage(data){
  return request({
    url: '/api/user/pageList',
    method: 'post',
    data: JSON.stringify(data),
    headers: {
      'Content-Type': 'application/json'
    }
  })
}

export function updateUserInfo(data) {
  return request({
    url:'/api/user',
    method: 'put',
    data: data
  })
}

export function createUser(data) {
  return request({
    url:'/api/user',
    method: 'post',
    data: data
  })
}

export function deletes(data) {
  return request({
    url: '/api/user/'+data.join(','),
    method: 'delete'
  })
}
