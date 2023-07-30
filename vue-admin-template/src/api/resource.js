import request from '@/utils/request'


export function Rselect() {
  return request({
    url: '/api/resource/select',
    method: 'get'
  })
}

export function listPage(data) {
  return request({
    url: '/api/resource/listPage',
    method: 'post',
    data
  })
}

export function createR(data) {

  return request({
    url:'/api/resource',
    method: 'post',
    data: data
  })

}

export function updateR(data) {

  return request({
    url:'/api/resource',
    method: 'put',
    data: data
  })

}
export function deleteR(data) {

  return request({
    url: '/api/resource/'+data.join(','),
    method:'delete'
  })

}
