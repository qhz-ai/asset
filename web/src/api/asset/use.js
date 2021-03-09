import request from '@/utils/request'

// 查询使用申请列表
export function listMyUse(query) {
  return request({
    url: '/asset/use/my',
    method: 'get',
    params: query
  })
}

// 查询使用申请列表
export function listUse(query) {
  return request({
    url: '/asset/use/list',
    method: 'get',
    params: query
  })
}

// 查询使用申请详细
export function getUse(id) {
  return request({
    url: '/asset/use/' + id,
    method: 'get'
  })
}

// 新增使用申请
export function addUse(data) {
  return request({
    url: '/asset/use',
    method: 'post',
    data: data
  })
}

// 修改使用申请
export function updateUse(data) {
  return request({
    url: '/asset/use',
    method: 'put',
    data: data
  })
}

// 删除使用申请
export function delUse(id) {
  return request({
    url: '/asset/use/' + id,
    method: 'delete'
  })
}

// 导出使用申请
export function exportUse(query) {
  return request({
    url: '/asset/use/export',
    method: 'get',
    params: query
  })
}

// 审核使用申请
export function checkUse(data) {
  return request({
    url: '/asset/use/check',
    method: 'put',
    data: data
  })
}
// 审核使用申请
export function revertUse(id) {
  return request({
    url: '/asset/use/revert/'+id,
    method: 'get'
  })
}
