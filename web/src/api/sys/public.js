import request from '@/utils/request'

// 查询用户列表
export function selectUser(query) {
  return request({
    url: '/sys/public/user',
    method: 'get',
    params: query
  })
}