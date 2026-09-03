import { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import client from '../api/client'
import { modules } from '../config/modules'
import Icon from '../components/Icon'
import { useAuth } from '../context/AuthContext'

export default function Dashboard() {
  const { user } = useAuth()
  const [counts, setCounts] = useState({})
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    let cancelled = false
    async function loadCounts() {
      setLoading(true)
      const results = await Promise.allSettled(
        modules.map((m) => client.get(m.endpoint))
      )
      if (cancelled) return
      const next = {}
      results.forEach((res, i) => {
        next[modules[i].key] = res.status === 'fulfilled' && Array.isArray(res.value.data) ? res.value.data.length : null
      })
      setCounts(next)
      setLoading(false)
    }
    loadCounts()
    return () => { cancelled = true }
  }, [])

  return (
    <div className="page">
      <div className="page-header">
        <div>
          <h1>Welcome back{user?.username ? `, ${user.username}` : ''}</h1>
          <p className="page-subtitle">Overview of your school records</p>
        </div>
      </div>

      <div className="stat-grid">
        {modules.map((m) => (
          <Link to={`/${m.key}`} key={m.key} className="stat-card">
            <div className="stat-icon">
              <Icon name={m.icon} size={22} />
            </div>
            <div className="stat-body">
              <span className="stat-value">{loading ? '…' : (counts[m.key] ?? '—')}</span>
              <span className="stat-label">{m.title}</span>
            </div>
          </Link>
        ))}
      </div>
    </div>
  )
}
