import './style.css'

// Mock Data (Replicating Java Demo)
const doctors = [
  { id: 101, name: "Dr. Alice Smith", dept: "Cardiology", spec: "Surgeon" },
  { id: 102, name: "Dr. Bob Johnson", dept: "Neurology", spec: "Specialist" },
  { id: 103, name: "Dr. Charlie Brown", dept: "Pediatrics", spec: "Consultant" }
];

const records = [
  { rid: 1, did: 101, date: "2026-05-01", status: "PRESENT" },
  { rid: 2, did: 101, date: "2026-05-02", status: "PRESENT" },
  { rid: 3, did: 101, date: "2026-05-03", status: "PRESENT" },
  { rid: 4, did: 101, date: "2026-05-04", status: "PRESENT" },
  { rid: 13, did: 101, date: "2026-06-01", status: "PRESENT" },

  { rid: 5, did: 102, date: "2026-05-01", status: "PRESENT" },
  { rid: 6, did: 102, date: "2026-05-02", status: "ABSENT" },
  { rid: 7, did: 102, date: "2026-05-03", status: "PRESENT" },
  { rid: 8, did: 102, date: "2026-05-04", status: "LEAVE" },

  { rid: 9, did: 103, date: "2026-05-01", status: "PRESENT" },
  { rid: 10, did: 103, date: "2026-05-02", status: "PRESENT" },
  { rid: 11, did: 103, date: "2026-05-03", status: "PRESENT" },
  { rid: 12, did: 103, date: "2026-05-04", status: "ABSENT" }
];

function getStats(did, month = 'all') {
  const filteredRecords = records.filter(r => 
    r.did === did && (month === 'all' || r.date.startsWith(month))
  );
  
  const total = filteredRecords.length;
  const present = filteredRecords.filter(r => r.status === 'PRESENT').length;
  const absent = filteredRecords.filter(r => r.status === 'ABSENT').length;
  const leave = filteredRecords.filter(r => r.status === 'LEAVE').length;
  const percent = total === 0 ? 0 : (present / total) * 100;
  
  return { total, present, absent, leave, percent };
}

function renderDashboard(month = 'all') {
  const dashboard = document.getElementById('dashboard');
  dashboard.innerHTML = '';
  
  doctors.forEach(doc => {
    const stats = getStats(doc.id, month);
    const ringClass = stats.percent >= 90 ? 'high' : (stats.percent < 75 ? 'low' : 'med');
    
    const card = document.createElement('div');
    card.className = 'card';
    card.innerHTML = `
      <div style="display: flex; justify-content: space-between; align-items: start;">
        <div class="doctor-info">
          <h3>${doc.name}</h3>
          <p>${doc.dept} • ${doc.spec}</p>
        </div>
        <div class="percentage-ring ${ringClass}">
          ${stats.percent.toFixed(0)}%
        </div>
      </div>
      <div class="stats">
        <div class="stat-item">
          <span class="stat-value">${stats.total}</span>
          <span class="stat-label">Days</span>
        </div>
        <div class="stat-item">
          <span class="stat-value">${stats.present}</span>
          <span class="stat-label">Present</span>
        </div>
        <div class="stat-item">
          <span class="stat-value">${stats.absent}</span>
          <span class="stat-label">Absent</span>
        </div>
        <div class="stat-item">
          <span class="stat-value">${stats.leave}</span>
          <span class="stat-label">Leave</span>
        </div>
      </div>
      <button class="view-details" data-id="${doc.id}" style="margin-top: 1.5rem; width: 100%;">View Records</button>
    `;
    dashboard.appendChild(card);
  });

  // Attach listeners
  document.querySelectorAll('.view-details').forEach(btn => {
    btn.onclick = () => showRecords(parseInt(btn.dataset.id), month);
  });
}

function showRecords(did, month = 'all') {
  const doc = doctors.find(d => d.id === did);
  const filtered = records.filter(r => 
    r.did === did && (month === 'all' || r.date.startsWith(month))
  );
  
  document.getElementById('reportContainer').style.display = 'block';
  document.getElementById('reportTitle').innerText = `Records for ${doc.name} (${month === 'all' ? 'All Time' : month})`;
  
  const tbody = document.getElementById('reportTableBody');
  tbody.innerHTML = filtered.map(r => `
    <tr>
      <td>${r.date}</td>
      <td><span class="status-pill status-${r.status}">${r.status}</span></td>
    </tr>
  `).join('');
  
  document.getElementById('reportContainer').scrollIntoView({ behavior: 'smooth' });
}

document.getElementById('monthFilter').onchange = (e) => {
  renderDashboard(e.target.value);
  document.getElementById('reportContainer').style.display = 'none';
};

document.getElementById('refreshBtn').onclick = () => {
  renderDashboard(document.getElementById('monthFilter').value);
};

// Initial Render
renderDashboard();
