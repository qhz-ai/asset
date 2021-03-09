import request from '@/utils/request'

// 查询发件箱消息列表
export function outMsg(query) {
  return request({
    url: '/sys/msg/out',
    method: 'get',
    params: query
  })
}


// 查询收件箱消息列表
export function inMsg(query) {
  return request({
    url: '/sys/msg/in',
    method: 'get',
    params: query
  })
}

// 查询消息列表
export function listMsg(query) {
  return request({
    url: '/sys/msg/list',
    method: 'get',
    params: query
  })
}

// 查询消息详细
export function getMsg(id) {
  return request({
    url: '/sys/msg/' + id,
    method: 'get'
  })
}

// 新增消息
export function addMsg(data) {
  return request({
    url: '/sys/msg',
    method: 'post',
    data: data
  })
}

// 修改消息
export function updateMsg(data) {
  return request({
    url: '/sys/msg',
    method: 'put',
    data: data
  })
}

// 删除消息
export function delMsg(id) {
  return request({
    url: '/sys/msg/' + id,
    method: 'delete'
  })
}

// 导出消息
export function exportMsg(query) {
  return request({
    url: '/sys/msg/export',
    method: 'get',
    params: query
  })
}


// 标记消息已读
export function readMsg(id) {
  return request({
    url: '/sys/msg/read/'+id,
    method: 'get'
  })
}
