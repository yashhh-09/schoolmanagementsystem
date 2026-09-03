const paths = {
  dashboard: 'M4 4h7v7H4V4Zm9 0h7v4h-7V4Zm0 7h7v9h-7v-9ZM4 14h7v6H4v-6Z',
  students: 'M12 3 2 8l10 5 8-4v6h2V8L12 3ZM6 12.5V17c0 1.657 2.686 3 6 3s6-1.343 6-3v-4.5l-6 3-6-3Z',
  teachers: 'M12 12a4 4 0 1 0 0-8 4 4 0 0 0 0 8Zm0 2c-4 0-8 2-8 5v2h16v-2c0-3-4-5-8-5Zm9-2v-2h-2v2h-2v2h2v2h2v-2h2v-2h-2Z',
  classes: 'M4 5h16v2H4V5Zm0 6h16v2H4v-2Zm0 6h10v2H4v-2Z',
  subjects: 'M6 4h9l5 5v11a1 1 0 0 1-1 1H6a1 1 0 0 1-1-1V5a1 1 0 0 1 1-1Zm8 1.5V9h3.5L14 5.5ZM8 12h8v1.5H8V12Zm0 3.5h8V17H8v-1.5Z',
  attendance: 'M9 11.5 7 13.5 3.5 10l1.4-1.4L7 10.7l3.6-3.6L12 8.5 9 11.5ZM13 4h7v2h-7V4Zm0 7h7v2h-7v-2Zm0 7h7v2h-7v-2ZM3 4h2v16H3V4Z',
  exams: 'M6 2h9l5 5v13a2 2 0 0 1-2 2H6a2 2 0 0 1-2-2V4a2 2 0 0 1 2-2Zm2 11 2.2 2.2L14.5 11l1.5 1.5-6 6L8 14.5 9.5 13Z',
  results: 'M4 20V10h4v10H4Zm6 0V4h4v16h-4Zm6 0v-7h4v7h-4Z',
  fees: 'M12 2a10 10 0 1 0 0 20 10 10 0 0 0 0-20Zm.75 15.5v1h-1.5v-1.06c-1.2-.2-2.15-.86-2.66-1.9l1.3-.75c.36.72 1.02 1.16 1.86 1.16.86 0 1.4-.4 1.4-.98 0-.66-.66-.92-1.7-1.22-1.4-.4-2.7-.9-2.7-2.5 0-1.14.86-1.98 2.2-2.2V7.5h1.5v1.06c1.02.2 1.8.78 2.24 1.6l-1.28.78c-.32-.56-.86-.9-1.56-.9-.76 0-1.24.36-1.24.9 0 .6.62.84 1.66 1.14 1.44.42 2.74.92 2.74 2.58 0 1.24-.9 2.1-2.36 2.34Z',
  library: 'M4 4h4v16H4V4Zm6 0h4v16h-4V4Zm6 .3 3.6 15.6-3.9.9L12 5.2 16 4.3Z',
  bookIssues: 'M6 2h9l5 5v13a2 2 0 0 1-2 2H6a2 2 0 0 1-2-2V4a2 2 0 0 1 2-2Zm2 8h8v1.6H8V10Zm0 3.5h8v1.6H8v-1.6Zm0 3.5h5v1.6H8V17Z',
  notices: 'M12 2a5 5 0 0 0-5 5v3.2c0 .8-.3 1.5-.86 2.1L4.5 14v1.5h15V14l-1.64-1.7A3 3 0 0 1 17 10.2V7a5 5 0 0 0-5-5Zm-2 16.5a2 2 0 0 0 4 0h-4Z',
  transport: 'M4 16V7a2 2 0 0 1 2-2h9l5 4v7h-2a2.5 2.5 0 0 1-5 0H10a2.5 2.5 0 0 1-5 0H4Zm3.5 1.8a.8.8 0 1 0 0-1.6.8.8 0 0 0 0 1.6Zm10 0a.8.8 0 1 0 0-1.6.8.8 0 0 0 0 1.6ZM13 7v3h4.4L15 7h-2Z',
  hostel: 'M12 3 2 10h2v10h6v-6h4v6h6V10h2L12 3Z',
  logout: 'M10 3h6a2 2 0 0 1 2 2v3h-2V5h-6v14h6v-3h2v3a2 2 0 0 1-2 2h-6a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2Zm5.5 6.5L18 12l-2.5 2.5-1.4-1.4.8-.8H4v-1.6h10.9l-.8-.8 1.4-1.4Z',
  add: 'M11 5h2v6h6v2h-6v6h-2v-6H5v-2h6V5Z',
  edit: 'M4 17.25V20h2.75L17.8 8.94l-2.75-2.75L4 17.25ZM19.7 7.04a1 1 0 0 0 0-1.42l-2.32-2.32a1 1 0 0 0-1.42 0l-1.83 1.83 2.75 2.75 1.82-1.84Z',
  trash: 'M6 7h12l-1 13.1a2 2 0 0 1-2 1.9H9a2 2 0 0 1-2-1.9L6 7Zm3-3h6l1 2h4v2H4V6h4l1-2Z',
  search: 'M10 4a6 6 0 1 0 3.8 10.66l4.77 4.77 1.4-1.4-4.76-4.77A6 6 0 0 0 10 4Zm0 2a4 4 0 1 1 0 8 4 4 0 0 1 0-8Z',
  menu: 'M3 6h18v2H3V6Zm0 5h18v2H3v-2Zm0 5h18v2H3v-2Z',
}

export default function Icon({ name, size = 20, className = '' }) {
  const d = paths[name]
  if (!d) return null
  return (
    <svg width={size} height={size} viewBox="0 0 24 24" fill="currentColor" className={className} aria-hidden="true">
      <path d={d} />
    </svg>
  )
}
