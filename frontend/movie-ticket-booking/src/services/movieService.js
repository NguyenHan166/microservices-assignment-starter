// // import axios from 'axios';

// export const fetchMovies = async () => {
//     return new Promise((resolve) => {
//         setTimeout(() => {
//             resolve(moviesData);
//         }, 1000); // Giả lập delay 1 giây
//     });
// };

// export const fetchMovieDetails = async (movieId) => {
//     return new Promise((resolve) => {
//         setTimeout(() => {
//             const movie = moviesData.find((m) => m.id === parseInt(movieId));
//             resolve(movie);
//         }, 1000); // Giả lập delay 1 giây
//     });
// };

// export const fetchShowtimes = (movieId) => {
//     return new Promise((resolve) => {
//         setTimeout(() => {
//             const showtimes = showtimesData.find((showtime) => showtime.movieId === parseInt(movieId));
//             resolve(showtimes);
//         }, 1000); // Giả lập delay 1 giây
//     });
// };


// export const fetchRoomAndSeats = (roomId) => {
//     return new Promise((resolve) => {
//         setTimeout(() => {
//             const room = roomsData.find((r) => r.id === parseInt(roomId));
//             resolve(room);
//         }, 1000); // Giả lập delay 1 giây
//     });
// };

// const moviesData = [
//     {
//         id: 1,
//         title: "Spider-Man: No Way Home",
//         description: "Spider-Man's identity is revealed, bringing his superhero duties into conflict with his normal life and putting those he loves most at risk.",
//         imageUrl: "https://example.com/spiderman.jpg",
//         showtimes: [
//             { id: '1', time: '12:00 PM' },
//             { id: '2', time: '03:00 PM' },
//             { id: '3', time: '06:00 PM' },
//         ]
//     },
//     {
//         id: 2,
//         title: "The Batman",
//         description: "Batman ventures into Gotham City's underworld when a sadistic killer leaves behind a trail of cryptic clues.",
//         imageUrl: "https://example.com/batman.jpg",
//         showtimes: [
//             { id: '1', time: '01:00 PM' },
//             { id: '2', time: '04:00 PM' },
//             { id: '3', time: '07:00 PM' },
//         ]
//     },
//     {
//         id: 3,
//         title: "Dune",
//         description: "A noble family becomes embroiled in a war for control of the galaxy's most valuable asset while its heir becomes troubled by visions of a dark future.",
//         imageUrl: "https://example.com/dune.jpg",
//         showtimes: [
//             { id: '1', time: '11:00 AM' },
//             { id: '2', time: '02:00 PM' },
//             { id: '3', time: '05:00 PM' },
//         ]
//     }
// ];

// const roomsData = [
//     {
//         id: 1,
//         name: "Room 1",
//         seats: [
//             { id: '1-1', status: 'available' },
//             { id: '1-2', status: 'available' },
//             { id: '1-3', status: 'booked' },
//             { id: '1-4', status: 'available' },
//             { id: '1-5', status: 'available' },
//             { id: '1-6', status: 'booked' },
//             { id: '1-7', status: 'available' },
//             { id: '1-8', status: 'available' },
//         ]
//     },
//     {
//         id: 2,
//         name: "Room 2",
//         seats: [
//             { id: '2-1', status: 'available' },
//             { id: '2-2', status: 'booked' },
//             { id: '2-3', status: 'available' },
//             { id: '2-4', status: 'available' },
//             { id: '2-5', status: 'booked' },
//             { id: '2-6', status: 'available' },
//             { id: '2-7', status: 'available' },
//             { id: '2-8', status: 'available' },
//         ]
//     }
// ];


// const showtimesData = [
//     {
//         movieId: 1, // Spider-Man: No Way Home
//         showtimes: [
//             { id: '1', roomId: 1, time: '12:00 PM' },
//             { id: '2', roomId: 1, time: '03:00 PM' },
//             { id: '3', roomId: 2, time: '06:00 PM' },
//         ]
//     },
//     {
//         movieId: 2, // The Batman
//         showtimes: [
//             { id: '1', roomId: 1, time: '01:00 PM' },
//             { id: '2', roomId: 2, time: '04:00 PM' },
//             { id: '3', roomId: 2, time: '07:00 PM' },
//         ]
//     },
//     {
//         movieId: 3, // Dune
//         showtimes: [
//             { id: '1', roomId: 1, time: '11:00 AM' },
//             { id: '2', roomId: 1, time: '02:00 PM' },
//             { id: '3', roomId: 2, time: '05:00 PM' },
//         ]
//     }
// ];



import axios from 'axios';

const API_BASE_URL = 'http://localhost:8222';

export const fetchMovies = async () => {
    try {
        const response = await axios.get(`${API_BASE_URL}/movies`);
        return response.data;
    } catch (error) {
        console.error('Error fetching movies:', error);
        throw error;
    }
};

export const fetchMovieDetails = async (movieId) => {
    try {
        const response = await axios.get(`${API_BASE_URL}/movies/${movieId}`);
        return response.data;
    } catch (error) {
        console.error('Error fetching movie details:', error);
        throw error;
    }
};

export const fetchShowtimes = async (movieId) => {
    try {
        const response = await axios.get(`${API_BASE_URL}/movies/showtimes/movie/${movieId}`);
        return response.data;
    } catch (error) {
        console.error('Error fetching showtimes:', error);
        throw error;
    }
};

export const fetchRoomAndSeats = async (roomId) => {
    try {
        const response = await axios.get(`${API_BASE_URL}/roomseats/${roomId}`);
        return response.data;
    } catch (error) {
        console.error('Error fetching room and seats:', error);
        throw error;
    }
};