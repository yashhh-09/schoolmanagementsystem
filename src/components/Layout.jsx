import { useState } from 'react'
import { NavLink, useNavigate, Outlet } from 'react-router-dom'
import { modules } from '../config/modules'
import { useAuth } from '../context/AuthContext'
import Icon from './Icon'

export default function Layout() {
  const { user, logout } = useAuth()
  const navigate = useNavigate()
  const [sidebarOpen, setSidebarOpen] = useState(false)

  const handleLogout = () => {
    logout()
    navigate('/login')
  }

  return (
    <div className="app-shell">
      <aside className={`sidebar ${sidebarOpen ? 'sidebar-open' : ''}`}>
        <div className="sidebar-brand">
          <div className="brand-mark">SM</div>
          <span>School MS</span>
        </div>
        <nav className="sidebar-nav">
          <NavLink to="/" end className={({ isActive }) => `nav-item ${isActive ? 'active' : ''}`} onClick={() => setSidebarOpen(false)}>
            <Icon name="dashboard" />
            <span>Dashboard</span>
          </NavLink>
          {modules.map((m) => (
            <NavLink
              key={m.key}
              to={`/${m.key}`}
              className={({ isActive }) => `nav-item ${isActive ? 'active' : ''}`}
              onClick={() => setSidebarOpen(false)}
            >
              <Icon name={m.icon} />
              <span>{m.title}</span>
            </NavLink>
          ))}
        </nav>
      </aside>

      {sidebarOpen && <div className="sidebar-scrim" onClick={() => setSidebarOpen(false)} />}

      <div className="main-col">
        <header className="topbar">
          <button className="icon-btn topbar-menu" onClick={() => setSidebarOpen(true)} aria-label="Open menu">
            <Icon name="menu" />
          </button>
          <div className="topbar-spacer" />
          <div className="topbar-user">
            <div className="user-avatar">{(user?.username || '?').slice(0, 1).toUpperCase()}</div>
            <div className="user-meta">
              <span className="user-name">{user?.username}</span>
              <span className="user-role">{user?.role}</span>
            </div>
            <button className="btn btn-ghost" onClick={handleLogout}>
              <Icon name="logout" size={18} />
              <span>Logout</span>
            </button>
          </div>
        </header>
        <main className="content">
          <Outlet />
        </main>
      </div>
    </div>
  )
}
