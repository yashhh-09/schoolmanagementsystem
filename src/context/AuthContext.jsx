import { createContext, useContext, useState, useCallback } from 'react'
import client from '../api/client'

const AuthContext = createContext(null)

export function AuthProvider({ children }) {
  const [user, setUser] = useState(() => {
    const raw = localStorage.getItem('sms_user')
    return raw ? JSON.parse(raw) : null
  })
  const [token, setToken] = useState(() => localStorage.getItem('sms_token'))

  const login = useCallback(async (username, password) => {
    const res = await client.post('/auth/login', { username, password })
    const { token: t, username: u, role } = res.data
    localStorage.setItem('sms_token', t)
    localStorage.setItem('sms_user', JSON.stringify({ username: u, role }))
    setToken(t)
    setUser({ username: u, role })
    return res.data
  }, [])

  const register = useCallback(async (username, password) => {
    const res = await client.post('/auth/register', { username, password })
    return res.data
  }, [])

  const logout = useCallback(() => {
    localStorage.removeItem('sms_token')
    localStorage.removeItem('sms_user')
    setToken(null)
    setUser(null)
  }, [])

  return (
    <AuthContext.Provider value={{ user, token, login, register, logout, isAuthenticated: !!token }}>
      {children}
    </AuthContext.Provider>
  )
}

export function useAuth() {
  const ctx = useContext(AuthContext)
  if (!ctx) throw new Error('useAuth must be used within AuthProvider')
  return ctx
}
