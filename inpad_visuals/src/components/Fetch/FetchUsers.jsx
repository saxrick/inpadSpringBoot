import useSWR from "swr";
const fetcher = (...args) => fetch(...args, {method: "GET", headers:{'Content-Type': 'application/json'}}).then((res) => res.json());

export default function FetchUsers(){
    const {
        data: users,
        error,
        isValidating,
    } = useSWR('http://localhost:8080/users/all', fetcher);

    // Handles error and loading state
    if (error) return <div className='failed'>failed to load</div>;
    if (isValidating) return <div className="Loading">Loading...</div>;
    console.log(users)
    // return projects
    return (
        <>
            {users && users.map((user) => <p key={user.id}> {user.username}</p>)}
        </>
    );
}