import request from '@/utils/request'


export function loadRoleIds() {
  return request({
    url: '/api/role/select',
    method: 'get'
  })
}

export function createRole(data) {
  return request({
    url: '/api/role',
    method: 'post',
    data: data
  })
}

export function updateRole(data) {
  return request({
    url: '/api/role',
    method: 'put',
    data: data
  })
}

export function deleteRole(data) {
  return request({
    url: '/api/role/'+data.join(','),
    method: 'delete'
  })
}
