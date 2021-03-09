import request from '@/utils/request'

// 查询无形资产列表
export function listAptitude(query) {
  return request({
    url: '/asset/aptitude/list',
    method: 'get',
    params: query
  })
}

// 查询无形资产详细
export function getAptitude(id) {
  return request({
    url: '/asset/aptitude/' + id,
    method: 'get'
  })
}

// 新增无形资产
export function addAptitude(data) {
  return request({
    url: '/asset/aptitude',
    method: 'post',
    data: data
  })
}

// 修改无形资产
export function updateAptitude(data) {
  return request({
    url: '/asset/aptitude',
    method: 'put',
    data: data
  })
}

// 删除无形资产
export function delAptitude(id) {
  return request({
    url: '/asset/aptitude/' + id,
    method: 'delete'
  })
}

// 导出无形资产
export function exportAptitude(query) {
  return request({
    url: '/asset/aptitude/export',
    method: 'get',
    params: query
  })
}