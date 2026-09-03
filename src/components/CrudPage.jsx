import { useEffect, useMemo, useState } from 'react'
import client from '../api/client'
import Modal from './Modal'
import Icon from './Icon'

const emptyFormFrom = (fields) =>
  fields.reduce((acc, f) => ({ ...acc, [f.name]: '' }), {})

function toColumnLabel(key) {
  return key.replace(/([A-Z])/g, ' $1').replace(/^./, (s) => s.toUpperCase())
}

export default function CrudPage({ module }) {
  const [rows, setRows] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')
  const [search, setSearch] = useState('')
  const [modalMode, setModalMode] = useState(null) // 'create' | 'edit' | null
  const [form, setForm] = useState(() => emptyFormFrom(module.fields))
  const [editingId, setEditingId] = useState(null)
  const [saving, setSaving] = useState(false)
  const [formError, setFormError] = useState('')
  const [deleteTarget, setDeleteTarget] = useState(null)
  const [deleting, setDeleting] = useState(false)

  useEffect(() => {
    setForm(emptyFormFrom(module.fields))
    setSearch('')
    fetchRows()
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [module.key])

  async function fetchRows() {
    setLoading(true)
    setError('')
    try {
      const res = await client.get(module.endpoint)
      setRows(Array.isArray(res.data) ? res.data : [])
    } catch (err) {
      setError(err.response?.data?.message || err.response?.data || 'Failed to load data. Is the backend running?')
    } finally {
      setLoading(false)
    }
  }

  const filteredRows = useMemo(() => {
    if (!search.trim()) return rows
    const q = search.toLowerCase()
    return rows.filter((row) =>
      module.columns.some((col) => String(row[col] ?? '').toLowerCase().includes(q))
    )
  }, [rows, search, module.columns])

  function openCreate() {
    setForm(emptyFormFrom(module.fields))
    setEditingId(null)
    setFormError('')
    setModalMode('create')
  }

  function openEdit(row) {
    const next = emptyFormFrom(module.fields)
    module.fields.forEach((f) => {
      next[f.name] = row[f.name] ?? ''
    })
    setForm(next)
    setEditingId(row.id)
    setFormError('')
    setModalMode('edit')
  }

  function closeModal() {
    setModalMode(null)
    setEditingId(null)
    setFormError('')
  }

  function handleChange(name, value) {
    setForm((f) => ({ ...f, [name]: value }))
  }

  function buildPayload() {
    const payload = {}
    module.fields.forEach((f) => {
      const raw = form[f.name]
      if (f.type === 'number') {
        payload[f.name] = raw === '' || raw === null ? null : Number(raw)
      } else {
        payload[f.name] = raw === '' ? null : raw
      }
    })
    return payload
  }

  async function handleSubmit(e) {
    e.preventDefault()
    setSaving(true)
    setFormError('')
    try {
      const payload = buildPayload()
      if (modalMode === 'create') {
        await client.post(module.endpoint, payload)
      } else {
        await client.put(`${module.endpoint}/${editingId}`, payload)
      }
      closeModal()
      fetchRows()
    } catch (err) {
      setFormError(err.response?.data?.message || err.response?.data || 'Save failed. Check the values and try again.')
    } finally {
      setSaving(false)
    }
  }

  async function confirmDelete() {
    if (!deleteTarget) return
    setDeleting(true)
    try {
      await client.delete(`${module.endpoint}/${deleteTarget.id}`)
      setDeleteTarget(null)
      fetchRows()
    } catch (err) {
      setError(err.response?.data?.message || err.response?.data || 'Delete failed.')
      setDeleteTarget(null)
    } finally {
      setDeleting(false)
    }
  }

  return (
    <div className="page">
      <div className="page-header">
        <div>
          <h1>{module.title}</h1>
          <p className="page-subtitle">{rows.length} record{rows.length === 1 ? '' : 's'}</p>
        </div>
        <div className="page-actions">
          <div className="search-box">
            <Icon name="search" size={18} />
            <input
              type="text"
              placeholder={`Search ${module.title.toLowerCase()}...`}
              value={search}
              onChange={(e) => setSearch(e.target.value)}
            />
          </div>
          <button className="btn btn-primary" onClick={openCreate}>
            <Icon name="add" size={18} />
            <span>Add New</span>
          </button>
        </div>
      </div>

      {error && <div className="alert alert-error">{String(error)}</div>}

      <div className="table-card">
        {loading ? (
          <div className="empty-state">Loading…</div>
        ) : filteredRows.length === 0 ? (
          <div className="empty-state">
            <p>No records found.</p>
            <button className="btn btn-secondary" onClick={openCreate}>Add the first one</button>
          </div>
        ) : (
          <div className="table-scroll">
            <table>
              <thead>
                <tr>
                  <th>#</th>
                  {module.columns.map((col) => (
                    <th key={col}>{toColumnLabel(col)}</th>
                  ))}
                  <th className="col-actions">Actions</th>
                </tr>
              </thead>
              <tbody>
                {filteredRows.map((row) => (
                  <tr key={row.id}>
                    <td className="text-muted">{row.id}</td>
                    {module.columns.map((col) => (
                      <td key={col}>{row[col] === null || row[col] === undefined || row[col] === '' ? '—' : String(row[col])}</td>
                    ))}
                    <td className="col-actions">
                      <button className="icon-btn" onClick={() => openEdit(row)} aria-label="Edit">
                        <Icon name="edit" size={18} />
                      </button>
                      <button className="icon-btn icon-btn-danger" onClick={() => setDeleteTarget(row)} aria-label="Delete">
                        <Icon name="trash" size={18} />
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>

      {modalMode && (
        <Modal
          title={modalMode === 'create' ? `Add ${module.title.slice(0, -1) || module.title}` : `Edit ${module.title.slice(0, -1) || module.title}`}
          onClose={closeModal}
          wide
          footer={
            <>
              <button className="btn btn-secondary" onClick={closeModal} type="button">Cancel</button>
              <button className="btn btn-primary" onClick={handleSubmit} disabled={saving}>
                {saving ? 'Saving…' : 'Save'}
              </button>
            </>
          }
        >
          {formError && <div className="alert alert-error">{String(formError)}</div>}
          <form className="form-grid" onSubmit={handleSubmit}>
            {module.fields.map((f) => (
              <div key={f.name} className={`form-field ${f.type === 'textarea' ? 'form-field-full' : ''}`}>
                <label htmlFor={f.name}>{f.label}{f.required && <span className="req">*</span>}</label>
                {f.type === 'select' ? (
                  <select
                    id={f.name}
                    value={form[f.name] ?? ''}
                    onChange={(e) => handleChange(f.name, e.target.value)}
                    required={f.required}
                  >
                    <option value="">Select…</option>
                    {f.options.map((opt) => (
                      <option key={opt} value={opt}>{opt}</option>
                    ))}
                  </select>
                ) : f.type === 'textarea' ? (
                  <textarea
                    id={f.name}
                    rows={3}
                    value={form[f.name] ?? ''}
                    onChange={(e) => handleChange(f.name, e.target.value)}
                    required={f.required}
                  />
                ) : (
                  <input
                    id={f.name}
                    type={f.type === 'number' ? 'number' : f.type === 'date' ? 'date' : 'text'}
                    value={form[f.name] ?? ''}
                    onChange={(e) => handleChange(f.name, e.target.value)}
                    required={f.required}
                  />
                )}
              </div>
            ))}
          </form>
        </Modal>
      )}

      {deleteTarget && (
        <Modal
          title="Delete record?"
          onClose={() => setDeleteTarget(null)}
          footer={
            <>
              <button className="btn btn-secondary" onClick={() => setDeleteTarget(null)}>Cancel</button>
              <button className="btn btn-danger" onClick={confirmDelete} disabled={deleting}>
                {deleting ? 'Deleting…' : 'Delete'}
              </button>
            </>
          }
        >
          <p>This will permanently delete <strong>{module.listLabel ? module.listLabel(deleteTarget) : `#${deleteTarget.id}`}</strong>. This action cannot be undone.</p>
        </Modal>
      )}
    </div>
  )
}
