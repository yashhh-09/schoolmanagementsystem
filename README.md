# School Management System — Frontend

A React (Vite) frontend for the `school-management` Spring Boot backend. Covers JWT login/register plus full CRUD for all 13 modules: Students, Teachers, Classes, Subjects, Attendance, Exams, Results, Fees, Library Books, Book Issues, Notices, Transport, Hostel.

## Stack
- React 18 + Vite
- React Router v6
- Axios (with a JWT request interceptor and automatic logout on 401)
- Plain CSS design system (no UI framework dependency)

## Setup

1. Make sure the backend is running (default `http://localhost:8080`).
2. Install dependencies:
   ```
   npm install
   ```
3. Copy the env file and adjust if your backend runs elsewhere:
   ```
   cp .env.example .env
   ```
   `.env`:
   ```
   VITE_API_URL=http://localhost:8080/api
   ```
4. Run the dev server:
   ```
   npm run dev
   ```
   Open `http://localhost:5173`.

## Login

Use the backend's seeded admin account:
- Username: `admin`
- Password: `admin123`

Or register a new account from the Register screen (`POST /api/auth/register`).

## Project structure

```
src/
  api/client.js        - axios instance, attaches Bearer token, handles 401
  context/AuthContext.jsx - login/register/logout state, persisted in localStorage
  config/modules.js    - single source of truth: one entry per backend resource
                          (endpoint, table columns, form fields/types)
  components/
    Layout.jsx          - sidebar + topbar shell
    CrudPage.jsx         - generic list + search + create/edit modal + delete,
                            driven entirely by a module config object
    Modal.jsx, Icon.jsx, ProtectedRoute.jsx
  pages/
    Login.jsx, Register.jsx, Dashboard.jsx
```

### Why one generic CRUD component?

All 13 backend resources expose the same shape of REST API
(`GET /`, `GET /{id}`, `POST /`, `PUT /{id}`, `DELETE /{id}`). Rather than
writing 13 nearly-identical page components, `CrudPage.jsx` renders a table,
search box, and a create/edit form purely from the `fields`/`columns`
declared for that module in `src/config/modules.js`. To add a new module,
add one entry to that file — no new component needed.

## Build

```
npm run build
npm run preview
```

## Notes
- CORS is already open (`*`) on the backend's `SecurityConfig`, so no proxy is required.
- The JWT is stored in `localStorage` under `sms_token`; a 401 response anywhere clears it and redirects to `/login`.
- `studentId`, `teacherId`, `subjectId`, `bookId` etc. are plain numeric fields (matching the backend entities, which don't expose nested objects) — enter the numeric ID of the related record.
